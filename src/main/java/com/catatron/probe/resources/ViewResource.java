package com.catatron.probe.resources;

import com.catatron.probe.Browsers;
import com.catatron.probe.Config;
import org.glassfish.jersey.server.mvc.Viewable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Component
@Path("/")
public class ViewResource {

    @Autowired
    Config config;

    @Autowired
    private Browsers browsers;

    @Context
    ServletContext context;

    @GET
    public Response index() throws URISyntaxException {
        return Response.temporaryRedirect(new URI("/evaluate")).build();
    }

    @GET
    @Path("/evaluate")
    @Produces(MediaType.TEXT_HTML)
    public Viewable getHello() throws IOException, URISyntaxException {
        final Map<String, Object> map = new HashMap<>();
        map.put("base_path", context.getContextPath());
        map.put("seleniumUrl", config.getSeleniumUrl());
        map.put("browsers", browsers.getAll());

        return new Viewable("/evaluate.ftl", map);
    }

}
