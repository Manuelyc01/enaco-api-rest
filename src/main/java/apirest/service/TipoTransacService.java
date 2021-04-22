package apirest.service;

import apirest.models.TipoTransaccion;

import java.util.List;

public interface TipoTransacService {
    List<TipoTransaccion> list();

    TipoTransaccion getById(Integer id);
}
