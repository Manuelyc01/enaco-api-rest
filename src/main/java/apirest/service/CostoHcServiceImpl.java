package apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apirest.models.CostoHojaCoca;
import apirest.repository.CostoHcRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CostoHcServiceImpl implements CostoHcService {

    @Autowired
    private final CostoHcRepository repository;

    public CostoHcServiceImpl(CostoHcRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CostoHojaCoca> list(){
        return repository.costos();
    }

    @Override
    public List<CostoHojaCoca> filterCostoHc(String cod_uniOpe){

        return repository.filterTipoHc(cod_uniOpe);
    }
}
