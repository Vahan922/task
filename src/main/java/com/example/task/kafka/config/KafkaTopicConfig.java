package com.example.task.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String MESSAGE = "message";
    public static final String VAHAN = "vahan";

    @Bean
    public NewTopic messageTopic() {
        return TopicBuilder.name(MESSAGE).build();
    }

    @Bean
    public NewTopic vahanTopic() {
        return TopicBuilder.name(VAHAN).build();
    }
}
