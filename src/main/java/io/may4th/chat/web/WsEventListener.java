package io.may4th.chat.web;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@AllArgsConstructor
@Component
@Log4j2
public class WsEventListener {

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("handleWebSocketConnectListener");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        log.info("handleWebSocketDisconnectListener");
    }
}
