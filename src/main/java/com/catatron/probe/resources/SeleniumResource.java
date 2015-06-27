package com.catatron.probe.resources;

import com.catatron.probe.ScriptEvaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/api")
public class SeleniumResource {

    private static final Logger LOG = LoggerFactory.getLogger(SeleniumResource.class);

    @Autowired
    private ScriptEvaluator scriptEvaluator;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/evaluate")
    public Response evaluateScript(
            @QueryParam("browser") String browserName,
            @QueryParam("version") String browserVersion,
            String javascript
    ) {
        try {
            LOG.info("Evaluating Javascript {} for browser {} {}", javascript, browserName, browserVersion);
            Object result = scriptEvaluator.evaluate(browserName, browserVersion, javascript);
            return Response.ok(result).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
