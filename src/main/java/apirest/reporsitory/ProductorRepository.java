package apirest.reporsitory;

import apirest.models.Productor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductorRepository extends JpaRepository<Productor, String> {
}
