package apirest.resource;

import apirest.models.Usuario;
import apirest.reporsitory.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class ResourceUsuario {


    private final UsuarioRepository repository;

    public ResourceUsuario(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/listar")
    public List<Usuario> listar(){
        List<Usuario> list = repository.findAll();
        return list;
    }

}
