package com.br.esp32.acesscontrol.domain;

import com.br.esp32.acesscontrol.enums.UserStatus;
import com.br.esp32.acesscontrol.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDomain {

    private Long id;

    private String cpf;

    private String name;

    private String email;

    private String rfidCode;

    private UserStatus status;

    private LocalDateTime lastAccess;

    private LocalDateTime creationDate;

    private LocalDateTime expireDate;

}
