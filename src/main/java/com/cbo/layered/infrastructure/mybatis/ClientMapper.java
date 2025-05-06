package com.cbo.layered.infrastructure.mybatis;

import com.cbo.layered.model.entity.Client;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface ClientMapper {

    @Select("""
            SELECT 
                client_id as clientId,
                name, 
                surname, 
                phone, 
                email, 
                updated_at as updatedAt, 
                registration_date as registrationDate
            FROM clients
            """)
    List<Client> findAll();

    @Select("""
            SELECT 
                client_id as clientId,
                name, 
                surname, 
                phone, 
                email, 
                updated_at as updatedAt, 
                registration_date as registrationDate
            FROM clients 
            WHERE client_id = #{clientId}
            """)
    Optional<Client> findById(@Param("clientId") UUID clientId);

    @Insert("""
            INSERT INTO clients (
                client_id, 
                name, 
                surname, 
                phone, 
                email, 
                registration_date,
                updated_at
            ) VALUES (
                #{clientId}, 
                #{name}, 
                #{surname}, 
                #{phone}, 
                #{email}, 
                #{registrationDate},
                #{updatedAt}
            )
            """)
    void save(Client client);

    @Update("""
            UPDATE clients 
            SET 
                name = #{name}, 
                surname = #{surname},
                phone = #{phone, jdbcType=VARCHAR},
                email = #{email},
                updated_at = #{updatedAt, jdbcType=TIMESTAMP}
            WHERE client_id = #{clientId}
            """)
    void update(@Param("clientId") UUID clientId, @Param("client") Client client);

    @Delete("DELETE FROM clients WHERE client_id = #{clientId}")
    void deleteById(@Param("clientId") UUID clientId);
}