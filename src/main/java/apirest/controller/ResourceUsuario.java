package apirest.controller;

import apirest.models.Usuario;
import apirest.repository.UsuarioRepository;
import apirest.service.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class ResourceUsuario {
    private final UsuarioService usuarioService;

    private final UsuarioRepository repository;

    public ResourceUsuario(UsuarioService usuarioService, UsuarioRepository repository) {
        this.usuarioService = usuarioService;
        this.repository = repository;
    }

    @GetMapping("/listar")
    public List<Usuario> listar(){
        return usuarioService.listar();
    }

}
