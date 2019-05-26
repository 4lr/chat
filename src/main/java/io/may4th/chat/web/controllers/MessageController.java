package io.may4th.chat.web.controllers;

import io.may4th.chat.security.api.CurrentUser;
import io.may4th.chat.security.api.Secured;
import io.may4th.chat.security.api.UserDetails;
import io.may4th.chat.security.api.exceptions.AccessDeniedException;
import io.may4th.chat.services.api.MessageService;
import io.may4th.chat.services.api.tos.MessageTO;
import io.may4th.chat.services.api.tos.NewMessageTO;
import io.may4th.chat.web.payload.ApiErrorResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/api/message")
@RestController
@ApiResponses({
    @ApiResponse(code = 400, message = "Bad Request", response = ApiErrorResponse.class)
})
@Secured
public class MessageController {

    @Autowired
    private final MessageService messageService;

    @ApiOperation("getMessagesByRoomId")
    @ApiResponse(code = 200, message = "OK", response = MessageTO.class, responseContainer = "List")
    @GetMapping
    public List<MessageTO> getMessagesByRoomId(@RequestParam UUID roomId) {
        return messageService.findAllByRoomId(roomId);
    }

    @ApiOperation("postMessage")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Created", response = MessageTO.class),
        @ApiResponse(code = 409, message = "Conflict")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageTO postMessage(@ApiIgnore @CurrentUser UserDetails currentUser, @RequestBody @Valid NewMessageTO newMessageTO) {
        if (!currentUser.getId().equals(newMessageTO.getUserId().toString())) {
            throw new AccessDeniedException();
        }
        return messageService.save(newMessageTO);
    }
}
