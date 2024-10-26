package com.moneygement.lyf.jarvis.common.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Enumeration;

public class HttpUtil {
    public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            return ip.split(",")[0].trim();
        }
        ip = request.getHeader("X-Real-IP");
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    public static String getHeadersAsString(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.append(headerName).append(": ").append(request.getHeader(headerName)).append(", ");
        }
        return headers.length() > 0 ? headers.substring(0, headers.length() - 2) : "";
    }

    public static String getHeadersAsString(HttpServletResponse response) {
        StringBuilder headers = new StringBuilder();
        for (String headerName : response.getHeaderNames()) {
            headers.append(headerName).append(": ").append(response.getHeader(headerName)).append(", ");
        }
        return headers.length() > 0 ? headers.substring(0, headers.length() - 2) : "";
    }

    public static String getHeadersAsJson(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder();
        headers.append("{");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.append("\"").append(headerName).append("\": ")
                   .append("\"").append(request.getHeader(headerName)).append("\"");
            if (headerNames.hasMoreElements()) {
                headers.append(", ");
            }
        }
        headers.append("}");
        return headers.toString();
    }

    public static String getHeadersAsJson(HttpServletResponse response) {
        StringBuilder headers = new StringBuilder();
        headers.append("{");
        var headerNames = response.getHeaderNames().iterator();
        while (headerNames.hasNext()) {
            String headerName = headerNames.next();
            headers.append("\"").append(headerName).append("\": ")
                   .append("\"").append(response.getHeader(headerName)).append("\"");
            if (headerNames.hasNext()) {
                headers.append(", ");
            }
        }
        headers.append("}");
        return headers.toString();
    }
}
