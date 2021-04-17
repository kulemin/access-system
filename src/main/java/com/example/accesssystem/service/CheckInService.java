package com.example.accesssystem.service;

import com.example.accesssystem.repositories.KeyStateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CheckInService {
    private final int MIN_ROOM_NUMBER = 1;
    private final int MAX_ROOM_NUMBER = 5;
    private final int MIN_KEY_NUMBER = 1;
    private final int MAX_KEY_NUMBER = 10000;
    private final int OPENED_DOOR = 200;
    private final int CLOSED_DOOR = 403;
    private final int ERROR = 500;
    @Autowired
    private KeyStateRepository keyStateRepository;

    public Integer check(int roomId, boolean entrance, int keyId) {
        log.info("Start check. KeyId: {}, RoomId: {}, entrance: {}", keyId, roomId, entrance);
        if (isNotCorrectRoom(roomId)) {
            log.info("Room {} is not correct", roomId);
            return ERROR;
        }
        if (isNotCorrectKey(keyId)) {
            log.info("Key {} is not correct", keyId);
            return ERROR;
        }

        Integer currentRoomId = keyStateRepository.getRoom(keyId);
        return currentRoomId == null
                ? keyOutDoor(roomId, entrance, keyId)
                : keyInRoom(currentRoomId, roomId, entrance, keyId);
    }

    private boolean isNotCorrectRoom(int roomId) {
        return roomId < MIN_ROOM_NUMBER || roomId > MAX_ROOM_NUMBER;
    }

    private boolean isNotCorrectKey(int keyId) {
        return keyId < MIN_KEY_NUMBER || keyId > MAX_KEY_NUMBER;
    }

    private Integer keyOutDoor(int roomId, boolean entrance, int keyId) {
        if (!entrance) {
            log.info("Key {} is out of room and can not leave room", keyId);
            return ERROR;
        }
        if (keyId % roomId == 0) {
            log.info("Key {} opens door {}", keyId, roomId);
            return OPENED_DOOR;
        }
        log.info("Key {} can not open door {}", keyId, roomId);
        return CLOSED_DOOR;
    }

    private Integer keyInRoom(int currentRoomId, int roomId, boolean entrance, int keyId) {
        if (entrance) {
            log.info("Key {} is already in room and can not enter any room", keyId);
            return ERROR;
        }
        if (currentRoomId == roomId) {
            log.info("Key {} opens the door {}", keyId, roomId);
            return OPENED_DOOR;
        }
        log.info("Key {} is in another room", keyId);
        return ERROR;
    }
}
