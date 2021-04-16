package apirest.service;

import apirest.models.Representante;

public interface RepresentanteService {
    Representante findByDni(String dni);
}
