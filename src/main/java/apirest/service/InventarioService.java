package apirest.service;

import apirest.models.*;

import java.text.ParseException;
import java.util.List;

public interface InventarioService {

    List<ActaRegistro> reporteActaRegistro(String cod, Integer per, String nombreUsuario) throws ParseException;

    void save(Inventario inventario);

    List<Inventario> list();

    List<Inventario> listByProductAlmacen(String cod_tipoHoja, String cod_uniOpe);

    List<Inventario> registrosFechaAlmacen(String inicio, String fin, String cod) throws ParseException;

    List<Inventario> registrosFechaAlmacenHc(String inicio, String fin, String cod, String codHc) throws ParseException;

    List<Inventario> listByProductAlmacenOne(String cod_tipoHoja, String cod_uniOpe);

    List<Inventario> stockHcAlmacen(String cod_tipoHoja, String cod_uniOpe);

    List<Inventario> listByUni(String cod_uniOpe);

}
