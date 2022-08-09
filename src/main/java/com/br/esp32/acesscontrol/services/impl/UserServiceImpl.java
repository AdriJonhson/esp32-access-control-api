package com.br.esp32.acesscontrol.services.impl;

import com.br.esp32.acesscontrol.domain.UserDomain;
import com.br.esp32.acesscontrol.dto.RabbitMessageDTO;
import com.br.esp32.acesscontrol.dto.RequestCardCodeDTO;
import com.br.esp32.acesscontrol.dto.UserRequestDTO;
import com.br.esp32.acesscontrol.enums.UserStatus;
import com.br.esp32.acesscontrol.models.User;
import com.br.esp32.acesscontrol.repositories.UserRepository;
import com.br.esp32.acesscontrol.services.UserService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final SimpMessagingTemplate webSocket;

    private final AmqpTemplate queueSender;
    private final Gson gson;
    @Value("${exchange.name}")
    private String exchangeName;

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
        User user = userRepository.findByRfidCode(rfidCode);

        if(Objects.isNull(user)) {
            return null;
        }

        user.setLastAccess(LocalDateTime.now());

        userRepository.save(user);

        return modelMapper.map(user, UserDomain.class);
    }
    @Override
    public void requestUserRfidCode(RequestCardCodeDTO requestCardCodeDTO) {
        log.info("UserService | requestUserRfidCode() | requestCardCodeDTO: {}", requestCardCodeDTO);

//        TODO: Config Enum with actions
//        REQUEST_CARD_CODE
        RabbitMessageDTO rabbitMessageDTO = RabbitMessageDTO.builder()
                .actionType("RCD")
                .registerIdentify(requestCardCodeDTO.getRegisterIdentify())
                .build();

        String request = gson.toJson(rabbitMessageDTO);

        log.info("UserService | requestUserRfidCode() | Sending request to rabbit | message: {}", request);

        queueSender.convertAndSend("amq.topic", exchangeName, request);
    }

    @Override
    public void sendUserRfidCode(RequestCardCodeDTO requestCardCodeDTO) {
        log.info("UserService | sendUserRfidCode() | received card code | requestCardCodeDTO : {}", requestCardCodeDTO);

        String request = gson.toJson(requestCardCodeDTO);

        log.info("UserService | sendUserRfidCode() | Sending request to rabbit | message: {}", request);

        webSocket.convertAndSend("/topic/user-waiting-card", request);
    }

}
