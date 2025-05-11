package payk96.micro_service1.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import payk96.micro_service1.dto.MessageRequest;
import payk96.micro_service1.dto.MessageStatusResponse;
import payk96.micro_service1.service.MessageService;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final KafkaTemplate<String, MessageRequest> kafkaTemplate;
    private final KafkaTemplate<String, MessageStatusResponse> replyKafkaTemplate;

    @Value("${spring.kafka.topics.message-topic}")
    private String topicName;

    @Value("${spring.kafka.topics.reply-topic}")
    private String replyTopic;

    @Override
    public MessageStatusResponse sendMessage(MessageRequest request) {
        log.info("Отправка сообщения в топик {}: {}", topicName, request);
        kafkaTemplate.send(topicName, request);

        return new MessageStatusResponse("Сообщение отправлено на обработку");
    }

    @KafkaListener(topics = "${spring.kafka.topics.reply-topic}")
    public void listenReply(MessageStatusResponse response) {
        log.info("Получен ответ от микросервиса-обработчика: {}", response.text());
    }
}
