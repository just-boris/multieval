package it.multieval;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.scanning.PackageNamesScanner;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import static org.glassfish.jersey.servlet.ServletProperties.FILTER_STATIC_CONTENT_REGEX;


public class Application extends ResourceConfig {

    public Application() {
        register(RequestContextFilter.class);
        register(JacksonFeature.class);
        register(FreemarkerMvcFeature.class);
        registerFinder(packageScanner());
        property(FILTER_STATIC_CONTENT_REGEX, "/static/.*");
    }

    private PackageNamesScanner packageScanner() {
        return new PackageNamesScanner(new String[]{getClass().getPackage().getName()}, true);
    }
}
