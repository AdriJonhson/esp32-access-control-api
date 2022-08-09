package com.br.esp32.acesscontrol.controllers;

import com.br.esp32.acesscontrol.domain.UserDomain;
import com.br.esp32.acesscontrol.dto.RequestCardCodeDTO;
import com.br.esp32.acesscontrol.dto.UserRequestDTO;
import com.br.esp32.acesscontrol.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDomain>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(value = "/verify-access", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDomain> verifyAccess(@RequestParam("rfidCode") String rfidCode) {
        UserDomain response = userService.verifyAccess(rfidCode);

        if(Objects.isNull(response)) {
            return ResponseEntity.notFound().build();
        }

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

    @PostMapping(value = "request-rfid-code",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> requestRfidCode(@RequestBody RequestCardCodeDTO request) {
        userService.requestUserRfidCode(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "send-rfid-code",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendRfidCode(@RequestBody RequestCardCodeDTO request) {
        userService.sendUserRfidCode(request);
        return ResponseEntity.ok().build();
    }
}
