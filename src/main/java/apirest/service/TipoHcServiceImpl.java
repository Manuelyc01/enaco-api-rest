package apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apirest.models.TipoHojaCoca;
import apirest.repository.TipoHcRepository;

import java.util.List;

@Service
public class TipoHcServiceImpl implements TipoHcService {

    @Autowired
    private final TipoHcRepository repository;

    public TipoHcServiceImpl(TipoHcRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TipoHojaCoca> list(){
        return repository.findAll();
    }

}
