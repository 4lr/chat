package io.may4th.chat.web.services;

import io.may4th.chat.services.api.MessageService;
import io.may4th.chat.services.api.RoomService;
import io.may4th.chat.services.api.tos.MessageTO;
import io.may4th.chat.services.api.tos.NewMessageTO;
import io.may4th.chat.services.api.tos.NewRoomTO;
import io.may4th.chat.services.api.tos.RoomTO;
import io.may4th.chat.web.security.UserDetailsImpl;
import io.may4th.chat.web.services.exceptions.PermissionDeniedException;
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

    @Autowired
    private final RoomService roomService;

    public List<MessageTO> getAllMessagesByRoomId(UserDetailsImpl currentUser, UUID roomId) {
        validateGetPermission(currentUser, roomId);
        return messageService.findAllByRoomId(roomId);
    }

    public MessageTO postMessage(UserDetailsImpl currentUser, NewMessageTO newMessageTO) {
        validateSingPermission(currentUser, newMessageTO.getUserId());
        return messageService.save(newMessageTO);
    }

    public RoomTO getRoom(UserDetailsImpl currentUser, UUID roomId) {
        validateGetPermission(currentUser, roomId);
        return roomService.findById(roomId);
    }

    public RoomTO postRoom(UserDetailsImpl currentUser, NewRoomTO newRoomTO) {
        validateSingPermission(currentUser, newRoomTO.getOwnerId());
        return roomService.save(newRoomTO);
    }

    private void validateGetPermission(UserDetailsImpl currentUser, UUID roomId) {
        if (!currentUser.getRooms().contains(roomId)) {
            throw new PermissionDeniedException();
        }
    }

    private void validateSingPermission(UserDetailsImpl currentUser, UUID sign) {
        if (!Objects.equals(currentUser.getUuid(), sign)) {
            throw new PermissionDeniedException();
        }
    }
}
