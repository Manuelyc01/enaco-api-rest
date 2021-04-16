package apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apirest.models.Productor;
import apirest.repository.ProductorRepository;

import java.util.List;

@Service
public class ProductorServiceImpl implements ProductorService{
    @Autowired
    private final ProductorRepository repository;

    public ProductorServiceImpl(ProductorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Productor> list(){
        return repository.findAll();
    }

    @Override
    public Productor findByCedula(String cedula){
        return repository.getOne(cedula);
    }
}
