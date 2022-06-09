package com.br.esp32.acesscontrol.models;

import com.br.esp32.acesscontrol.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "rfid_code")
    private String rfidCode;

    @Column(name = "status")
    @ColumnDefault("ACTIVE")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "last_access")
    private LocalDateTime lastAccess;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "expire_date")
    private LocalDateTime expireDate;

}
