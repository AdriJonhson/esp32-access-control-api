package com.br.esp32.acesscontrol.controllers;

import com.br.esp32.acesscontrol.domain.UserDomain;
import com.br.esp32.acesscontrol.dto.UserRequestDTO;
import com.br.esp32.acesscontrol.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDomain>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(value = "/verify-access", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDomain> verifyAccess(@RequestParam("rfidCode") String rfidCode) {
        return ResponseEntity.ok(userService.verifyAccess(rfidCode));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDomain> save(@RequestBody UserRequestDTO request) {
        return ResponseEntity.ok(userService.save(request));
    }

    @PatchMapping(value = "{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDomain> update(@PathVariable Long userId,
                                             @RequestBody UserRequestDTO request) {
        return ResponseEntity.ok(userService.update(userId, request));
    }

}
