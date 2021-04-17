package com.example.accesssystem.controllers;

import com.example.accesssystem.repositories.KeyStateRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CheckInControllerTest {

    @Autowired
    private CheckInController checkInController;
    @Autowired
    private KeyStateRepository keyStateRepository;

    @Test
    public void check_keyOutRoomTryEnterCorrectRoom_return200() {
        ResponseEntity<Integer> check = checkInController.check(1, true, 1);

        assertEquals(200, check.getBody());
    }

    @Test
    public void check_keyInRoomTryEnterAnyRoom_return500() {
        int keyId = 1;
        keyStateRepository.enterRoom(keyId, 4);

        ResponseEntity<Integer> check = checkInController.check(1, true, keyId);

        assertEquals(500, check.getBody());
        keyStateRepository.leaveRoom(keyId);
    }

    @Test
    public void check_keyInRoomTryLeaveThisRoom_return200() {
        int keyId = 1;
        int roomId = 4;
        keyStateRepository.enterRoom(keyId, roomId);

        ResponseEntity<Integer> check = checkInController.check(roomId, false, keyId);

        assertEquals(200, check.getBody());
    }

    @Test
    public void check_keyOutRoomTryEnterIncorrectRoom_return403() {
        int keyId = 2;
        int roomId = 5;

        ResponseEntity<Integer> check = checkInController.check(roomId, true, keyId);

        assertEquals(403, check.getBody());
    }

    @Test
    public void check_keyIdIsNotCorrect_return500() {
        int keyId = 10001;
        int roomId = 5;

        ResponseEntity<Integer> check = checkInController.check(roomId, true, keyId);

        assertEquals(500, check.getBody());
    }

    @Test
    public void check_roomIdIsNotCorrect_return500() {
        int keyId = 1;
        int roomId = 6;

        ResponseEntity<Integer> check = checkInController.check(roomId, true, keyId);

        assertEquals(500, check.getBody());
    }
}