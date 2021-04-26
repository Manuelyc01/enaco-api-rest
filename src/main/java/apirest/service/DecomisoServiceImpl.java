package apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import apirest.models.Decomiso;
import apirest.models.Inventario;
import apirest.models.Movimiento;
import apirest.repository.DecomisoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DecomisoServiceImpl implements DecomisoService {
    @Autowired
    private final DecomisoRepository repository;

    @Autowired
    private final MovimientoService movimientoService;
    @Autowired
    private final InventarioService inventarioService;

    public DecomisoServiceImpl(DecomisoRepository repository, MovimientoService movimientoService, InventarioService inventarioService) {
        this.repository = repository;
        this.movimientoService = movimientoService;
        this.inventarioService = inventarioService;
    }

    @Override
    public List<Decomiso> numMoviento(){
        return repository.list(PageRequest.of(0,1));
    }
    @Override
    public List<Decomiso> listar(){
        return repository.listar();
    }

    @Override
    @Transactional
    public String save(Decomiso decomiso) {
        return repository.saveDecomiso(decomiso.getId_usuario().getId_usuario(),decomiso.getCod_uniOpe().getCod_uniOpe()
        ,decomiso.getLugarOperativo(),decomiso.getLugarDecomiso(),decomiso.getDecomisante(),decomiso.getDocReferencia(),
                decomiso.getComentario(),decomiso.getCod_tipoHoja().getCod_tipoHoja(),decomiso.getCantidad(),decomiso.getCantidadNeta());
    }

    @Override
    public List<Decomiso> listByUni(String cod){
        return repository.lisByUni(cod);
    }
    @Override
    public List<Decomiso> registrosFechaDecomiso(String inicio, String fin, String cod) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date ini= format.parse(inicio.replace("T"," "));
        Date fn= format.parse(fin.replace("T"," "));
        return repository.filterFechaDecomisoHc(ini,fn,cod);
    }
    @Override
    public List<Decomiso> listByProductDecomiso(String cod_tipoHoja, String cod_uniOpe){
        return repository.listByProductDecomiso(cod_tipoHoja,cod_uniOpe);
    }
    @Override
    public List<Decomiso> registrosFechaDecomisoHc(String inicio, String fin, String cod, String codHc) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date ini= format.parse(inicio.replace("T"," "));
        Date fn= format.parse(fin.replace("T"," "));
        return repository.filterFechaDecomisoHc(ini,fn,cod,codHc);
    }

}
