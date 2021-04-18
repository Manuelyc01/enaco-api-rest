package apirest.controller;

import apirest.models.Usuario;
import apirest.repository.UsuarioRepository;
import apirest.service.UsuarioService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class RestUsuario {
    private final UsuarioService usuarioService;

  public RestUsuario(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar")
    public List<Usuario> listar(){
        return usuarioService.listar();
    }

    @GetMapping("/findByUsua/{nameUser}")
    public Usuario findByUsua(@PathVariable String nameUser) {
        return usuarioService.findByUsua(nameUser);
    }

    @RequestMapping(method =RequestMethod.POST, value = "/registro")
    public void save( @RequestBody Usuario usuario){
        usuarioService.registrar(usuario);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Integer id){
        usuarioService.eliminar(id);
    }

    @GetMapping("/findById/{id}")
    public Usuario findById(@PathVariable Integer id) {
        return usuarioService.findById(id);
    }

    @RequestMapping(method =RequestMethod.PUT, value = "/actualizar/{id}")
    public void update(@PathVariable Integer id ,@RequestBody Usuario usuario){
        usuarioService.update(id,usuario);
    }

    @RequestMapping(method =RequestMethod.PUT, value = "/compra/{id}")
    public void compra(@PathVariable Integer id){
        usuarioService.compra(id);
    }



}
