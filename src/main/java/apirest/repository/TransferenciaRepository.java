package apirest.repository;

import apirest.models.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {

    @Query("SELECT u from Transferencia u where u.origen.cod_uniOpe=?1 order by u.fecha desc")
    List<Transferencia> lisByUni(String cod);

    @Query("SELECT u from Transferencia u where u.fecha between ?1 and ?2 and u.origen.cod_uniOpe=?3 order by u.fecha desc")
    List<Transferencia> filterFechaTransferenciaHc(Date ini, Date fn, String cod);

    @Query("SELECT u from Transferencia u where  u.cod_tipoHoja.cod_tipoHoja=?1 and  u.origen.cod_uniOpe=?2 order by u.fecha desc ")
    List<Transferencia> listByProductTransferencia(String cod_tipoHoja, String cod_uniOpe);

    @Query("select u from Transferencia  u where u.fecha between ?1 and ?2 and u.origen.cod_uniOpe=?3 and u.cod_tipoHoja.cod_tipoHoja=?4 order by u.fecha desc ")
    List<Transferencia> filterFechaTransferenciaHc(Date ini, Date fn, String cod, String codHc);

    @Query(value = "{call sp_registrarTransferencia(" +
            ":id_usuarioIn," +
            ":origenIn," +
            ":destinoIn," +
            ":transportistaIn," +
            ":placaVehiculoIn," +
            ":comentarioIn," +
            ":cod_tipoHojaIn," +
            ":cantidadIn)}",nativeQuery = true)
    String saveTransferencia(
            @Param("id_usuarioIn")Integer id_usuarioIn,
            @Param("origenIn")String origenIn,
            @Param("destinoIn")String destinoIn,
            @Param("transportistaIn")String transportistaIn,
            @Param("placaVehiculoIn")String placaVehiculoIn,
            @Param("comentarioIn")String comentarioIn,
            @Param("cod_tipoHojaIn")String cod_tipoHojaIn,
            @Param("cantidadIn")Double cantidadIn);

    @Query("SELECT u from Transferencia u order by u.id_transferencia desc")
    List<Transferencia> listar();
}
