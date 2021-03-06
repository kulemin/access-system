package com.example.accesssystem.repositories;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class KeyStateRepositoryImpl implements KeyStateRepository{
    private Map <Integer, Integer> KeyRoom = new HashMap<>();
    @Override
    public Integer getRoom(int keyId) {
        return KeyRoom.get(keyId);
    }

    @Override
    public void enterRoom(int keyId, int roomId) {
        KeyRoom.put(keyId, roomId);
    }

    @Override
    public void leaveRoom(int keyId) {
        KeyRoom.remove(keyId);
    }
}
