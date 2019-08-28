package io.may4th.chat.web.services;

import io.may4th.chat.web.payload.WsMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WsService {

    @Autowired
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    public void send(WsMessage message) {
        simpMessageSendingOperations.convertAndSend(message.getDestination(), message);
    }
}
