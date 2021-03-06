package apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apirest.models.Agencia;
import apirest.repository.AgenciaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AgenciaServiImpl implements AgenciaService {

    @Autowired
    private final AgenciaRepository repository;

    public AgenciaServiImpl(AgenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Agencia> list(){
        return repository.findAll();
    }
}
