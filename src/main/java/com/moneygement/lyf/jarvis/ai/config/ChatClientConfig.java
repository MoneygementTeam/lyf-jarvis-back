package com.moneygement.lyf.jarvis.ai.config;

import lombok.Getter;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ChatClientConfig {
    @Bean
    ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }

    @Value("${ai.chat-client.system-text.voice-text-parsing}")
    private String voiceTextParsingSystemText;

    @Value("${ai.chat-client.system-text.optimal-route}")
    private String optimalRouteSystemText;
}
