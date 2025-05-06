package com.cbo.layered.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Client implements Serializable {
    private UUID clientId;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Date updatedAt;
    private Date registrationDate;
}
