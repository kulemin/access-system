package com.example.accesssystem.repositories;

import java.util.Map;

public interface KeyStateRepository {
    Integer getRoom(int keyId);
    void enterRoom(int keyId, int roomId);
    void leaveRoom(int keyId);
}
