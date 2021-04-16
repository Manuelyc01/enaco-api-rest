package apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apirest.models.TipoTransaccion;
import apirest.repository.TipoTransacRepository;

import java.util.List;

@Service
public class TipoTransacServiceImpl implements TipoTransacService{
    @Autowired
    private final TipoTransacRepository repository;

    public TipoTransacServiceImpl(TipoTransacRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TipoTransaccion> list(){
        return repository.findAll();
    }

    @Override
    public TipoTransaccion getById(Integer id){
        return repository.getOne(id);
    }
}
