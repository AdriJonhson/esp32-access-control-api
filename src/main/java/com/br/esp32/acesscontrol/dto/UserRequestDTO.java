package com.br.esp32.acesscontrol.dto;

import com.br.esp32.acesscontrol.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private Long id;

    private String cpf;

    private String name;

    private String email;

    private String rfidCode;

    private UserStatus status;

    private LocalDateTime expireDate;

}
