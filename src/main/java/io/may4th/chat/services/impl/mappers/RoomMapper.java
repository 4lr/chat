package io.may4th.chat.services.impl.mappers;

import io.may4th.chat.domain.api.entities.Room;
import io.may4th.chat.services.api.tos.NewRoomTO;
import io.may4th.chat.services.api.tos.RoomTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper extends BaseMapper<Room, RoomTO, NewRoomTO> {
}
