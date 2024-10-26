package com.moneygement.lyf.jarvis.ai.service;

import static com.moneygement.lyf.jarvis.common.util.HttpClientUtil.post;

import com.moneygement.lyf.jarvis.ai.domain.PointPickRequest;
import com.moneygement.lyf.jarvis.ai.domain.PointPickResponse;
import com.moneygement.lyf.jarvis.ai.domain.VoiceTextParsingRequest;
import com.moneygement.lyf.jarvis.ai.domain.VoiceTextParsingResponse;
import com.moneygement.lyf.jarvis.ai.config.ChatClientConfig;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiServiceImpl implements AiService {

    private final ChatClient chatClient;
    private final ChatClientConfig config;

    public <T> T sendChatToAi(String systemText, String prompt, Class<T> entity) {
        T response;

        try {
            response = chatClient.prompt()
                      .system(systemText)
                      .user(prompt != null ? prompt : "")
                      .call()
                      .entity(entity);
        } catch (Exception e) {
            log.error("sendChatToAi Failed! {}", e.getMessage());
            throw new RuntimeException(e);
        }

        log.info("sendChatToAi Request: {}, Response: {}", prompt, response);
        return response;
    }

    @Override
    public VoiceTextParsingResponse callGptForVoiceTextParsing(VoiceTextParsingRequest request) {
        return sendChatToAi(config.getVoiceTextParsingSystemText(), request.toString(), VoiceTextParsingResponse.class);
    }

    @Override
    public PointPickResponse callRagForPointPick(PointPickRequest request) {
        //TODO RAG 호출 및 목적지 리스트 String[] 생성
        ResponseEntity<Object> object = post(null, "url", request.getParsedVoiceText());
        return new PointPickResponse((List) object.getBody());
    }

    @Override
    public PointPickResponse callGptForPointPick(PointPickRequest request) {
        return sendChatToAi(config.getOptimalRouteSystemText(), request.getParsedVoiceText(), PointPickResponse.class);
    }
}

