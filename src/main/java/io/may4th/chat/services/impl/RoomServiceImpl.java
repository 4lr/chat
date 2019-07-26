package io.may4th.chat.services.impl;

import io.may4th.chat.domain.api.RoomRepository;
import io.may4th.chat.domain.api.entities.Room;
import io.may4th.chat.services.api.RoomService;
import io.may4th.chat.services.api.tos.NewRoomTO;
import io.may4th.chat.services.api.tos.RoomTO;
import io.may4th.chat.services.impl.mappers.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl extends BaseService<Room, RoomTO, NewRoomTO> implements RoomService {

    @Autowired
    private final RoomMapper roomMapper;
    @Autowired
    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomMapper roomMapper, RoomRepository roomRepository) {
        super(roomMapper, roomRepository);
        this.roomMapper = roomMapper;
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomTO save(NewRoomTO newRoomTO) {
        return super.save(newRoomTO);
    }
}
