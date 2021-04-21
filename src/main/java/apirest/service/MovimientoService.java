package apirest.service;

import apirest.models.Movimiento;

import java.util.List;

public interface MovimientoService {
    List<Movimiento> list();

    Movimiento findById(Integer id);
}
