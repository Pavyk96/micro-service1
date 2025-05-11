package payk96.micro_service1.service;

import payk96.micro_service1.dto.MessageRequest;
import payk96.micro_service1.dto.MessageStatusResponse;

public interface MessageService {
    MessageStatusResponse sendMessage(MessageRequest request);
}
