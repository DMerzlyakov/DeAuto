package com.serverauto.server.repositories;

import com.serverauto.server.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Репозиторий для работы с таблицей Car;
 * Необходим для работы Spring Data Rest, а так же контроллеров и сервисов
 */
@RepositoryRestResource(collectionResourceRel = "cars", path = "cars")
public interface CarRepository extends JpaRepository<Car, Long> {


    void deleteById(Long id);

    @Query
    List<Car> findAllByAvailable(@Param("available") Boolean available);

}
