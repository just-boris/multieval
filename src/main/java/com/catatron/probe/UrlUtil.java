package com.catatron.probe;

import javax.servlet.http.HttpServletRequest;

import static java.lang.String.format;
import static org.springframework.util.StringUtils.isEmpty;

public class UrlUtil {

    public static String getHost(HttpServletRequest request) {
        String host = request.getHeader("Host");
        return isEmpty(host) ? request.getRemoteHost() : host;
    }

    public static String getUrlTo(HttpServletRequest request, String path) {
        return format("%s://%s%s", request.getScheme(), getHost(request), path);
    }
}
