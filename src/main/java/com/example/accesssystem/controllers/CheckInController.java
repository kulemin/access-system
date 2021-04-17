package com.example.accesssystem.controllers;

import com.example.accesssystem.service.CheckInService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("check")
public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    @PostMapping
    public ResponseEntity<Integer> check (@RequestParam("roomId") int roomId,
                                 @RequestParam("entrance") boolean entrance,
                                 @RequestParam("keyId") int keyId) {
        return  ResponseEntity.ok(checkInService.check(roomId, entrance, keyId));
    }
}
