package apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apirest.models.Representante;
import apirest.repository.RepresentanteRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RepresentanteServiceImpl implements RepresentanteService {

    private final RepresentanteRepository repository;

    public RepresentanteServiceImpl(RepresentanteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Representante findByDni(String dni){
        return repository.findByDni(dni);
    }
    @Override
    public Representante findById(Integer id){
        return repository.getOne(id);
    }
}
