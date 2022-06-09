package com.br.esp32.acesscontrol.services.impl;

import com.br.esp32.acesscontrol.domain.UserDomain;
import com.br.esp32.acesscontrol.dto.UserRequestDTO;
import com.br.esp32.acesscontrol.enums.UserStatus;
import com.br.esp32.acesscontrol.models.User;
import com.br.esp32.acesscontrol.repositories.UserRepository;
import com.br.esp32.acesscontrol.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<UserDomain> findAll() {
        return userRepository
                .findAll()
                .parallelStream()
                .map(u -> modelMapper.map(u, UserDomain.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDomain save(UserRequestDTO userRequest) {
        User user = modelMapper.map(userRequest, User.class);

        user.setCreationDate(LocalDateTime.now());
        user.setStatus(UserStatus.ACTIVE);

        user = userRepository.save(user);

        return modelMapper.map(user, UserDomain.class);
    }

    @Override
    public UserDomain update(long id, UserRequestDTO userRequest) {
        User userUpdate = modelMapper.map(userRequest, User.class);

        userUpdate.setId(id);

        userRepository.save(userUpdate);

        return modelMapper.map(userUpdate, UserDomain.class);
    }

    @Override
    public UserDomain verifyAccess(String rfidCode) {
        return modelMapper.map(userRepository.findByRfidCode(rfidCode), UserDomain.class);
    }
}
