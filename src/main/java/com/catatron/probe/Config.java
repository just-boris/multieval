package com.catatron.probe;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.springframework.util.StringUtils.isEmpty;

@Component
public class Config {

    @Value("${selenium.host}")
    private String seleniumHost;

    @Value("${selenium.port}")
    private int seleniumPort;

    @Value("${selenium.username}")
    private String seleniumUsername;

    @Value("${selenium.password}")
    private String seleniumPassword;

    public String getSeleniumHost() {
        return seleniumHost;
    }

    public int getSeleniumPort() {
        return seleniumPort;
    }

    public String getSeleniumUsername() {
        return seleniumUsername;
    }

    public String getSeleniumPassword() {
        return seleniumPassword;
    }

    public URL getSeleniumUrl() throws URISyntaxException, MalformedURLException {
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http");
        builder.setHost(getSeleniumHost());
        builder.setPort(getSeleniumPort());
        if(!isEmpty(getSeleniumUsername())) {
            if(!isEmpty(getSeleniumPassword())) {
                builder.setUserInfo(getSeleniumUsername(), getSeleniumPassword());
            } else {
                builder.setUserInfo(getSeleniumUsername());
            }
        }
        builder.setPath("/wd/hub");
        return builder.build().toURL();
    }
}
