package apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apirest.models.Estado;
import apirest.repository.EstadoRepository;

import java.util.List;

@Service
public class EstadoServiceImpl implements EstadoService {

    @Autowired
    private final EstadoRepository estadoRepository;

    public EstadoServiceImpl(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public List<Estado> estados(){
        return estadoRepository.findAll();
    }
}
