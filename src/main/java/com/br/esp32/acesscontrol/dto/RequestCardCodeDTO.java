package com.br.esp32.acesscontrol.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCardCodeDTO {
    private UUID registerIdentify;
    private String rfidCode;
}
