package apirest.service;

import apirest.models.Estado;
import apirest.models.Usuario;
import apirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UsuarioRepository repository;
    @Autowired
    public UsuarioServiceImpl(UsuarioRepository repository,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }
    @Override
    public List<Usuario> listar(){
        List<Usuario> list = repository.findAll();
        return list;
    }
    @Override
    public Usuario findByUsua(String usua) {
        return repository.findByUsua(usua);
    }

    @Override
    public Usuario registrar(Usuario usuario) {
        usuario.setPassw(bCryptPasswordEncoder.encode(usuario.getPassw()));
        return repository.save(usuario);
    }
    @Override
    public void eliminar(Integer id_usuario){
        Usuario usuario = repository.getOne(id_usuario);
        Estado e=new Estado();
        e.setId_estado(2);
        usuario.setId_estado(e);
        repository.save(usuario);
    }
    @Override
    public Usuario findById(Integer id){
        return  repository.getOne(id);
    }
    @Override
    public void update(Integer id, Usuario usuario){
        Usuario u1 = repository.getOne(id);
        String s= "";
        u1.setNombre(usuario.getNombre());
        u1.setTelefono(usuario.getTelefono());
        u1.setCorreo(usuario.getCorreo());
        u1.setId_rol(usuario.getId_rol());
        u1.setCod_uniOpe(usuario.getCod_uniOpe());
        u1.setUsua(usuario.getUsua());
        if (usuario.getPassw()!= s){
            u1.setPassw(bCryptPasswordEncoder.encode(usuario.getPassw()));
        }
        u1.setId_estado(usuario.getId_estado());
        u1.setSerie_compra(usuario.getSerie_compra());

        repository.save(u1);

    }
    @Override
    @Transactional
    public void compra(Integer id){
        Usuario u= repository.getOne(id);
        if (u.getNum_compras()!=null){
            u.setNum_compras(u.getNum_compras()+1);
        }else {
            u.setNum_compras(+1);
        }
        repository.save(u);
    }
}
