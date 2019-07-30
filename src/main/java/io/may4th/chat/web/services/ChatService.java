package io.may4th.chat.web.services;

import io.may4th.chat.security.api.exceptions.AccessDeniedException;
import io.may4th.chat.services.api.MessageService;
import io.may4th.chat.services.api.tos.MessageTO;
import io.may4th.chat.services.api.tos.NewMessageTO;
import io.may4th.chat.web.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ChatService {

    @Autowired
    private final MessageService messageService;

    public List<MessageTO> getAllMessagesByRoomId(UserDetailsImpl currentUser, UUID roomId) {
        validateGetMessagesPermission(currentUser, roomId);
        return messageService.findAllByRoomId(roomId);
    }

    public MessageTO postMessage(UserDetailsImpl currentUser, NewMessageTO newMessageTO) {
        validateSingPermission(currentUser, newMessageTO.getUserId());
        return messageService.save(newMessageTO);
    }

    private void validateGetMessagesPermission(UserDetailsImpl currentUser, UUID roomId) {
        if (!currentUser.getRooms().contains(roomId)) {
            throw new AccessDeniedException();
        }
    }

    private void validateSingPermission(UserDetailsImpl currentUser, UUID sign) {
        if (!Objects.equals(currentUser.getUuid(), sign)) {
            throw new AccessDeniedException();
        }
    }
}
