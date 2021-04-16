package apirest.service;

import apirest.models.Productor;

import java.util.List;

public interface ProductorService {
    List<Productor> list();

    Productor findByCedula(String cedula);
}
