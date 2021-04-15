package apirest.repository;

import apirest.models.Representante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentanteRepository extends JpaRepository<Representante, Integer> {
    public Representante findByDni(String dni);
}
