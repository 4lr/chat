package io.may4th.chat.web.controllers;

import io.may4th.chat.services.MessageService;
import io.may4th.chat.web.tos.ApiError;
import io.may4th.chat.web.tos.MessageTO;
import io.may4th.chat.web.tos.PostMessageTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Log4j2
@RequestMapping("/api/message")
@RestController
@ApiResponses({
    @ApiResponse(code = 400, message = "Bad Request", response = ApiError.class)
})
public class MessageController {

    @Autowired
    private final MessageService messageService;

    @ApiOperation("getMessagesByRoomId")
    @ApiResponse(code = 200, message = "OK", response = MessageTO.class, responseContainer = "List")
    @GetMapping
    public List<MessageTO> getMessagesByRoomId(@RequestParam @NotNull UUID roomId) {
        return messageService.findAllByRoomId(roomId);
    }

    @ApiOperation("postMessage")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Created", response = MessageTO.class),
        @ApiResponse(code = 409, message = "Conflict")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageTO postMessage(@RequestBody @Valid PostMessageTO postMessageTO) {
        return messageService.save(postMessageTO);
    }
}
