package io.may4th.chat.web.controllers;

import io.may4th.chat.security.api.CurrentUser;
import io.may4th.chat.security.api.Secured;
import io.may4th.chat.services.api.tos.RoomTO;
import io.may4th.chat.services.api.tos.NewRoomTO;
import io.may4th.chat.web.payload.ApiErrorResponse;
import io.may4th.chat.web.security.UserDetailsImpl;
import io.may4th.chat.web.services.ChatService;
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
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/api/room")
@RestController
@ApiResponses({
    @ApiResponse(code = 400, message = "Bad Request", response = ApiErrorResponse.class)
})
@Secured
public class RoomController {

    @Autowired
    private final ChatService chatService;

    @ApiOperation("getRoom")
    @ApiResponse(code = 200, message = "OK", response = RoomTO.class)
    @GetMapping
    public RoomTO getRoom(
        @ApiIgnore @CurrentUser UserDetailsImpl currentUser,
        @RequestParam UUID roomId
    ) {
        return chatService.getRoom(currentUser, roomId);
    }

    @ApiOperation("postRoom")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Created", response = RoomTO.class),
        @ApiResponse(code = 409, message = "Conflict")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomTO postRoom(
        @ApiIgnore @CurrentUser UserDetailsImpl currentUser,
        @RequestBody @Valid NewRoomTO newRoomTO
    ) {
        return chatService.postRoom(currentUser, newRoomTO);
    }
}
