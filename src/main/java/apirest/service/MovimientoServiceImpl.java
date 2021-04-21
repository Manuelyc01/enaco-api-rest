package apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apirest.models.Movimiento;
import apirest.repository.MovimientoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovimientoServiceImpl implements MovimientoService{

    @Autowired
    private final MovimientoRepository repository;

    public MovimientoServiceImpl(MovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Movimiento> list(){
        return repository.findAll();
    }

    @Override
    public Movimiento findById(Integer id){
        return repository.getOne(id);
    }

}
