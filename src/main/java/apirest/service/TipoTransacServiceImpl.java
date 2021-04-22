package apirest.service;

import org.springframework.stereotype.Service;
import apirest.models.TipoTransaccion;
import apirest.repository.TipoTransacRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TipoTransacServiceImpl implements TipoTransacService{

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
