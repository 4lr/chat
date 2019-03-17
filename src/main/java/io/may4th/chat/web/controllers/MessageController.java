package io.may4th.chat.web.controllers;

import io.may4th.chat.services.MessageService;
import io.may4th.chat.web.tos.MessageTO;
import io.may4th.chat.web.tos.PostMessageTO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Log4j2
@RequestMapping("/api/message")
@RestController
public class MessageController {

    @Autowired
    private final MessageService messageService;

    @GetMapping
    public List<MessageTO> getMessagesByRoomId(@RequestParam UUID roomId) {
        return messageService.findAllByRoomId(roomId);
    }

    @PostMapping
    public MessageTO postMessage(PostMessageTO messageTO) {
        return messageService.save(messageTO);
    }
}
