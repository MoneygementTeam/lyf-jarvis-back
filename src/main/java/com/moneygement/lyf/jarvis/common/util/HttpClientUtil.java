package com.moneygement.lyf.jarvis.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;

import static com.moneygement.lyf.jarvis.common.util.LoggingUtil.printRestTemplateErrorLog;
import static com.moneygement.lyf.jarvis.common.util.LoggingUtil.printRestTemplateOneLineLog;

@Slf4j
@Component
public class HttpClientUtil {

    private static final RestTemplate restTemplate = new RestTemplate();

    /**
     * GET 방식으로 호출
     * @param uri
     * @return
     */
    public static ResponseEntity<Object> get(HttpHeaders headers, String uri) {
        try {
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<Object> response = restTemplate.exchange(uri, HttpMethod.GET, entity, Object.class);
            printRestTemplateOneLineLog(HttpMethod.GET.name(), uri, headers, null, response);
            return response;
        } catch (RestClientException e) {
            printRestTemplateErrorLog(e, HttpMethod.GET.name(), uri, headers, null);
            return createErrorResponse(e);
        }
    }

    /**
     * POST 형태로 데이터 전송
     * @param uri
     * @param data
     * @return
     */
    public static ResponseEntity<Object> post(HttpHeaders headers, String uri, Object data) {
        try {
            HttpEntity<Object> entity = new HttpEntity<>(data, headers);
            ResponseEntity<Object> response = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class);
            printRestTemplateOneLineLog(HttpMethod.POST.name(), uri, headers, data, response);
            return response;
        } catch (RestClientException e) {
            printRestTemplateErrorLog(e, HttpMethod.POST.name(), uri, headers, data);
            return createErrorResponse(e);
        }
    }

    public static ResponseEntity<Object> createErrorResponse(Throwable e) {
        if (e instanceof HttpClientErrorException || e instanceof HttpServerErrorException) {
            return new ResponseEntity<>(e.getMessage(), ((RestClientResponseException) e).getStatusCode());
        } else {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
