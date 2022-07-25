package com.serverauto.server.repositories;

import com.serverauto.server.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Репозиторий для работы с таблицей Employee;
 * Необходим для работы Spring Data Rest, а так же контроллеров и сервисов
 */
@RepositoryRestResource(collectionResourceRel = "employees", path = "employees")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query
    Employee findByEmailAndPassword(@Param("email") String name, @Param("pass") String pass);


}
