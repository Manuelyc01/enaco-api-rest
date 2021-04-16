package apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apirest.models.Sucursal;
import apirest.repository.SucursalRepository;

import java.util.List;

@Service
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    private final SucursalRepository repository;

    public SucursalServiceImpl(SucursalRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Sucursal> list(){
        return repository.findAll();
    }
}
