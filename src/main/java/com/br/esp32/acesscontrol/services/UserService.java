package com.br.esp32.acesscontrol.services;

import com.br.esp32.acesscontrol.domain.UserDomain;
import com.br.esp32.acesscontrol.dto.UserRequestDTO;

import java.util.List;

public interface UserService {

    List<UserDomain> findAll();
    UserDomain save(UserRequestDTO userRequest);
    UserDomain update(long id, UserRequestDTO userRequest);
    UserDomain verifyAccess(String rfidCode);
}
