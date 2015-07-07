package it.multieval.resources;

import it.multieval.Browsers;
import it.multieval.Config;
import it.multieval.UrlUtil;
import org.glassfish.jersey.server.mvc.Viewable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
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

    @Context
    HttpServletRequest request;

    @Context
    HttpServletResponse response;

    @GET
    public Response index() throws IOException {
        response.sendRedirect(UrlUtil.getUrlTo(request, "/evaluate"));
        return Response.ok().build();
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
