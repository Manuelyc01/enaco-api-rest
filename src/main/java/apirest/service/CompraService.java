package apirest.service;

import apirest.models.Compra;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

public interface CompraService {
    @Transactional
    void save(Compra compra);

    List<Compra> list();

    List<Compra> listByIdUsuario(Integer id);

    Compra findById(Integer id);

    List<Compra> listByUni(String cod);

    List<Compra> registrosFechaCompra(String inicio, String fin, String cod) throws ParseException;

    List<Compra> listByProductCompra(String cod_tipoHoja, String cod_uniOpe);

    List<Compra> registrosFechaCompraHc(String inicio, String fin, String cod, String codHc) throws ParseException;
}
