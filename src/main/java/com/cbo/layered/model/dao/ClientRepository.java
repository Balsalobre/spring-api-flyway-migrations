package com.cbo.layered.model.dao;

import com.cbo.layered.model.entity.Client;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
// import org.springframework.data.PagingAndSortingRepository;

import java.util.UUID;

@Repository
public interface ClientRepository extends ListCrudRepository<Client, UUID> {
}
