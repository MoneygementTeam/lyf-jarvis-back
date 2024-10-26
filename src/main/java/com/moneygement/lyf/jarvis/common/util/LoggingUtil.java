package com.moneygement.lyf.jarvis.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

import static com.moneygement.lyf.jarvis.common.util.HttpUtil.getClientIP;
import static com.moneygement.lyf.jarvis.common.util.HttpUtil.getHeadersAsJson;

@Slf4j
public class LoggingUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void printOneLineLog(HttpServletRequest request, HttpServletResponse response, String requestBody, String responseBody) {
        log.info("[One-line] {\"request\":{\"method\":\"{}\",\"ip\":\"{}\",\"uri\":\"{}\",\"reqHeaders\":{},\"reqBody\":{}} \"response\":{\"resStatus\":\"{}\",\"resHeaders\":{},\"resBody\":{}}}",
                 request.getMethod(),
                 getClientIP(request),
                 request.getRequestURI(),
                 getHeadersAsJson(request),
                 requestBody,
                 response.getStatus(),
                 getHeadersAsJson(response),
                 responseBody);
    }

    public static void printRestTemplateErrorLog(Throwable e, String method, String uri, HttpHeaders headers, Object data) {
        Map<String, Object> logData = new HashMap<>();
        Map<String, Object> reqMap = new HashMap<>();
        if (e instanceof HttpClientErrorException) {
            logData.put("exception", e.getMessage());
        } else {
            logData.put("exception", e.getStackTrace());
        }
        reqMap.put("method", method);
        reqMap.put("uri", uri);
        reqMap.put("reqHeaders", headers);
        reqMap.put("reqBody", data);
        logData.put("request", reqMap);

        try {
            log.error("[RestTemplate Exception] {}", objectMapper.writeValueAsString(logData));
        } catch (JsonProcessingException ex) {
            log.error("Error while converting log data to JSON!", ex);
            if (e instanceof HttpClientErrorException) {
                log.error("\n[RestTemplate Exception] {\"exception\":\"{}\",\"request\":{\"method\":\"{}\",\"uri\":\"{}\",\"reqHeaders\":{},\"reqBody\":{}}}",
                          e.getMessage(), method, uri, headers, data);
            } else {
                log.error("\n[RestTemplate Exception] {\"exception\":\"{}\",\"request\":{\"method\":\"{}\",\"uri\":\"{}\",\"reqHeaders\":{},\"reqBody\":{}}}",
                          e, method, uri, headers, data, e.getMessage());
            }
        }
    }

    public static void printRestTemplateOneLineLog(String method, String uri, HttpHeaders headers, Object data, ResponseEntity<?> response) {
        Map<String, Object> logData = new HashMap<>();
        Map<String, Object> reqMap = new HashMap<>();
        Map<String, Object> resMap = new HashMap<>();
        reqMap.put("method", method);
        reqMap.put("uri", uri);
        reqMap.put("reqHeaders", headers);
        reqMap.put("reqBody", data);
        resMap.put("resStatus", response.getStatusCode());
        resMap.put("resHeaders", response.getHeaders());
        resMap.put("resBody", response.getBody());
        logData.put("request", reqMap);
        logData.put("response", resMap);

        try {
            log.info("[RestTemplate One-line] {}", objectMapper.writeValueAsString(logData));
        } catch (JsonProcessingException e) {
            log.error("Error while converting log data to JSON!", e);
            log.info("[RestTemplate One-line] {\"request\":{\"method\":\"{}\",\"uri\":\"{}\",\"reqHeaders\":{},\"reqBody\":{}},\"response\":{\"resStatus\":\"{}\",\"resHeaders\":{},\"resBody\":{}}}",
                     method, uri, headers, data, response.getStatusCode(), response.getHeaders(), response.getBody());
        }
    }
}
