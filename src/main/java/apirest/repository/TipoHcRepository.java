package apirest.repository;

import apirest.models.TipoHojaCoca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoHcRepository extends JpaRepository<TipoHojaCoca, String> {
}
