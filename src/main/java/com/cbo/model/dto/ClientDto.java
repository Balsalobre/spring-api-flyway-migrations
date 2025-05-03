package com.cbo.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class ClientDto implements Serializable {
    private UUID clientId = UUID.randomUUID();
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Date updatedAt = new Date();
    private Date registrationDate = new Date();
}
