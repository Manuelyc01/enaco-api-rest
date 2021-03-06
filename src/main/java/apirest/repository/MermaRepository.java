package apirest.repository;

import apirest.models.Merma;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MermaRepository extends JpaRepository<Merma, Integer> {

    @Query("SELECT u FROM Merma u order by u.id_merma desc")
    public List<Merma> list(Pageable pageable);

    @Query("SELECT u from Merma u where u.cod_uniOpe.cod_uniOpe=?1 order by u.fecha desc")
    List<Merma> lisByUni(String cod);

    @Query("SELECT u from Merma u where u.fecha between ?1 and ?2 and u.cod_uniOpe.cod_uniOpe=?3 order by u.fecha desc")
    List<Merma> filterFechaMermaHc(Date ini, Date fn, String cod);

    @Query("SELECT u from Merma u where  u.cod_tipoHoja.cod_tipoHoja=?1 and  u.cod_uniOpe.cod_uniOpe=?2 order by u.fecha desc ")
    List<Merma> listByProductMerma(String cod_tipoHoja, String cod_uniOpe);

    @Query("select u from Merma  u where u.fecha between ?1 and ?2 and u.cod_uniOpe.cod_uniOpe=?3 and u.cod_tipoHoja.cod_tipoHoja=?4 order by u.fecha desc ")
    List<Merma> filterFechaMermaHc(Date ini, Date fn, String cod, String codHc);

    @Query(value = "{call sp_registrarMerma(" +
            ":id_usuarioIn," +
            ":cod_uniOpeIn,"+
            ":comentarioIn," +
            ":cod_tipoHojaIn," +
            ":cantidadIn," +
            ":cantidadNetaIn)}",nativeQuery = true)
    String saveMerma(
            @Param("id_usuarioIn")Integer id_usuario,
            @Param("cod_uniOpeIn")String cod_uniOpe,
            @Param("comentarioIn")String comentario,
            @Param("cod_tipoHojaIn")String cod_tipoHoja,
            @Param("cantidadIn")Double cantidad,
            @Param("cantidadNetaIn")Double cantidadNeta);

    @Query("SELECT u FROM Merma u order by u.id_merma desc")
    List<Merma> listar();
}
