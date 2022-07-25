package com.serverauto.server.repositories;


import com.serverauto.server.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Репозиторий для работы с таблицей Sale;
 * Необходим для работы Spring Data Rest, а так же контроллеров и сервисов
 */
@RepositoryRestResource(collectionResourceRel = "sales", path = "sales")
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
