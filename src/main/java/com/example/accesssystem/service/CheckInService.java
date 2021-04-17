package com.example.accesssystem.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CheckInService {
    public Integer check(int roomId, boolean entrance, int keyId) {
        log.info("Start check. KeyId: {}, RoomId: {}, entrance: {}", keyId, roomId, entrance);
        return 200;
    }
}
