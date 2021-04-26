package apirest.repository;

import apirest.models.Compra;
import apirest.models.Decomiso;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DecomisoRepository extends JpaRepository<Decomiso, Integer> {

    @Query("SELECT u FROM Decomiso u order by u.id_decomiso desc")
    public List<Decomiso> list(Pageable pageable);

    @Query("SELECT u from Decomiso u order by u.id_decomiso desc ")
    public List<Decomiso> listar();

    @Query("SELECT u from Decomiso u where u.cod_uniOpe.cod_uniOpe=?1 order by u.fecha desc")
    List<Decomiso> lisByUni(String cod);

    @Query("SELECT u from Decomiso u where u.fecha between ?1 and ?2 and u.cod_uniOpe.cod_uniOpe=?3 order by u.fecha desc")
    List<Decomiso> filterFechaDecomisoHc(Date ini, Date fn, String cod);

    @Query("SELECT u from Decomiso u where  u.cod_tipoHoja.cod_tipoHoja=?1 and  u.cod_uniOpe.cod_uniOpe=?2 order by u.fecha desc ")
    List<Decomiso> listByProductDecomiso(String cod_tipoHoja, String cod_uniOpe);

    @Query("select u from Decomiso  u where u.fecha between ?1 and ?2 and u.cod_uniOpe.cod_uniOpe=?3 and u.cod_tipoHoja.cod_tipoHoja=?4 order by u.fecha desc ")
    List<Decomiso> filterFechaDecomisoHc(Date ini, Date fn, String cod, String codHc);

    @Query(value = "{call sp_registrarDecomiso(" +
            ":id_usuarioIn," +
            ":cod_uniOpeIn," +
            ":lugarOperativoIn," +
            ":lugarDecomisoIn," +
            ":decomisanteIn," +
            ":docReferenciaIn," +
            ":comentarioIn," +
            ":cod_tipoHojaIn," +
            ":cantidadIn," +
            ":cantidadNetaIn)}",nativeQuery = true)
    String saveDecomiso(
            @Param("id_usuarioIn")Integer id_usuario,
            @Param("cod_uniOpeIn")String cod_uniOpe,
            @Param("lugarOperativoIn")String lugarOperativo,
            @Param("lugarDecomisoIn")String lugarDecomiso,
            @Param("decomisanteIn")String decomisante,
            @Param("docReferenciaIn")String docReferencia,
            @Param("comentarioIn")String comentario,
            @Param("cod_tipoHojaIn")String cod_tipoHoja,
            @Param("cantidadIn")Double cantidad,
            @Param("cantidadNetaIn")Double cantidadNeta);
}
