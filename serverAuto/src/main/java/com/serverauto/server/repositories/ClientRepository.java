package com.serverauto.server.repositories;

import com.serverauto.server.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Репозиторий для работы с таблицей Client;
 * Необходим для работы Spring Data Rest, а так же контроллеров и сервисов
 */
@RepositoryRestResource(collectionResourceRel = "clients", path = "clients")
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByFirstNameAndLastName(@Param("name") String name1, @Param("name2") String name2);


}


