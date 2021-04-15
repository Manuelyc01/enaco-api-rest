package apirest.service;

import apirest.models.Usuario;
import apirest.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Usuario> listar(){
        List<Usuario> list = repository.findAll();
        return list;
    }
}
