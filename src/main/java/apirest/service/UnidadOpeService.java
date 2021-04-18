package apirest.service;

import apirest.models.UnidadOperativa;

import java.util.List;

public interface UnidadOpeService {
    List<UnidadOperativa> listar();

    UnidadOperativa findByCod(String cod);

    void save(UnidadOperativa unidadOperativa);

    void saveCajaBoveda(String cod, double monto, Integer tipo);

    //FILTRADO POR LOCALIDAD
    List<UnidadOperativa> listarAgeSu(String cod_agencia, String cod_sucursal);

    List<UnidadOperativa> listarAgencia(String cod_agencia);

    List<UnidadOperativa> listarSucursal(String cod_sucursal);
}
