package payk96.micro_service1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payk96.micro_service1.dto.MessageRequest;
import payk96.micro_service1.dto.MessageStatusResponse;
import payk96.micro_service1.service.MessageService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService service;

    @PostMapping("/message")
    public MessageStatusResponse sendMessage(@RequestBody MessageRequest request) {
        return service.sendMessage(request);
    }
}
