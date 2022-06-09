package com.br.esp32.acesscontrol.repositories;

import com.br.esp32.acesscontrol.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    User findByRfidCode(String rfidCode);

}
