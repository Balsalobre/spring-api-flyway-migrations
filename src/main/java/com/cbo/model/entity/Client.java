package com.cbo.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.io.Serializable;
import java.sql.Types;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "clients")
public class Client implements Serializable {
    @Id
    @JdbcTypeCode(Types.VARCHAR)
    @Column(name = "client_id")
    private UUID clientId = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "updated_at")
    private Date updatedAt = new Date();
    
    @Column(name = "registration_date")
    private Date registrationDate = new Date();
}
