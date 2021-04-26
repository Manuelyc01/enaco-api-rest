package apirest.repository;

import apirest.models.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {

    @Query("SELECT u FROM Compra u where u.id_usuario.id_usuario=?1 order by u.id_compra desc ")
    public List<Compra> listCompraByIdUsuario(Integer id);

    @Query("SELECT u from Compra u order by u.id_compra desc ")
    public List<Compra> list();

    @Query("SELECT u from Compra u where u.cod_uniOpe.cod_uniOpe=?1 order by u.fecha desc")
    List<Compra> lisByUni(String cod);

    @Query("SELECT u from Compra u where u.fecha between ?1 and ?2 and u.cod_uniOpe.cod_uniOpe=?3 order by u.fecha desc")
    List<Compra> filterFechaCompraHc(Date inicio, Date fin, String cod);

    @Query("SELECT u from Compra u where  u.cod_tipoHoja.cod_tipoHoja=?1 and  u.cod_uniOpe.cod_uniOpe=?2 order by u.fecha desc ")
    List<Compra> listByProductCompra(String cod_tipoHoja, String cod_uniOpe);

    @Query("select u from Compra  u where u.fecha between ?1 and ?2 and u.cod_uniOpe.cod_uniOpe=?3 and u.cod_tipoHoja.cod_tipoHoja=?4 order by u.fecha desc ")
    List<Compra> filterFechaCompraHc(Date ini, Date fn, String cod, String codHc);

    @Query(value = "{call sp_registrarCompra(" +
            ":num_liquidacionIn," +
            ":cedula_productorIn," +
            ":dni_repreIn," +
            ":cod_uniOpeIn," +
            ":cod_tipoHojaIn," +
            ":pesoBrutoIn," +
            ":taraIn," +
            ":humedadIn," +
            ":pesoNetoIn," +
            ":valorCompraIn," +
            ":igvIn," +
            ":totalCompraIn," +
            ":sonIn," +
            ":id_usuarioIn," +
            ":id_repreIn)}",nativeQuery = true)
    String saveCompra(
            @Param("num_liquidacionIn")String num_liquidacion,
            @Param("cedula_productorIn")String cedula_productor,
            @Param("dni_repreIn")String dni_repre,
            @Param("cod_uniOpeIn")String cod_uniOpe,
            @Param("cod_tipoHojaIn")String cod_tipoHojaIn,
            @Param("pesoBrutoIn")Double pesoBrutoIn,
            @Param("taraIn")Double taraIn,
            @Param("humedadIn")Double humedadIn,
            @Param("pesoNetoIn")Double pesoNetoIn,
            @Param("valorCompraIn")Double valorCompraIn,
            @Param("igvIn")Double igvIn,
            @Param("totalCompraIn")Double totalCompraIn,
            @Param("sonIn")String sonIn,
            @Param("id_usuarioIn")Integer id_usuarioIn,
            @Param("id_repreIn")Integer id_repreIn
    );
}
