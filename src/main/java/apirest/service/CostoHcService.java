package apirest.service;

import apirest.models.CostoHojaCoca;

import java.util.List;

public interface CostoHcService {
    List<CostoHojaCoca> list();



    List<CostoHojaCoca> filterCostoHc(String cod_uniOpe);
}
