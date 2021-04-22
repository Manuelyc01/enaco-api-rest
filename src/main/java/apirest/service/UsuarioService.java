package apirest.service;

import apirest.models.Usuario;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UsuarioService {
    List<Usuario> listar();

    Usuario findByUsua(String usua);

    Usuario registrar(Usuario usuario);

    void eliminar(Integer id_usuario);

    Usuario findById(Integer id);

    void update(Integer id, Usuario usuario);

    @Transactional
    void compra(Integer id);
}
