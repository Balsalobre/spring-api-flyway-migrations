package com.cbo.model.dao;

import com.cbo.model.entity.Client;
import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.PagingAndSortingRepository;

import java.util.UUID;

public interface ClientDAO extends CrudRepository<Client, UUID> {
}
