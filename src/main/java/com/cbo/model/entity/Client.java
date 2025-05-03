package com.cbo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "clients")
public class Client implements Serializable {
    @Id
    @Column(name = "client_id", columnDefinition = "CHAR(36)")
    private UUID clientId = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String email;

    @Column(name = "email")
    private Date registrationDate;
}
