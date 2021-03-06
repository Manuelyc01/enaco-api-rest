package apirest.repository;

import apirest.models.CostoHojaCoca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostoHcRepository extends JpaRepository<CostoHojaCoca, Integer> {

    @Query("SELECT u FROM CostoHojaCoca u where u.estado=1 and u.cod_uniOpe.cod_uniOpe= ?1")
    public List<CostoHojaCoca> filterTipoHc(String cod_uniOpe);

    @Query("SELECT u FROM CostoHojaCoca u where u.estado=1")
    public List<CostoHojaCoca> costos();
}
