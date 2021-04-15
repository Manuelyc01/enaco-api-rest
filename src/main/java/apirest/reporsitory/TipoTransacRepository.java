package apirest.reporsitory;

import apirest.models.TipoTransaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTransacRepository extends JpaRepository<TipoTransaccion, Integer> {
}
