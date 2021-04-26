package apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import apirest.models.Demasia;
import apirest.models.Inventario;
import apirest.models.Movimiento;
import apirest.repository.DemasiaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DemasiaServiceImpl implements DemasiaService {

    @Autowired
    private final DemasiaRepository repository;

    @Autowired
    private final MovimientoService movimientoService;
    @Autowired
    private final InventarioService inventarioService;
    public DemasiaServiceImpl(DemasiaRepository repository, MovimientoService movimientoService, InventarioService inventarioService) {
        this.repository = repository;
        this.movimientoService = movimientoService;
        this.inventarioService = inventarioService;
    }

    @Override
    @Transactional
    public String save(Demasia demasia){
        return
                repository.saveDemasia(demasia.getId_usuario().getId_usuario(),demasia.getCod_uniOpe().getCod_uniOpe()
                ,demasia.getDocumento(),demasia.getComentario(),demasia.getCod_tipoHoja().getCod_tipoHoja(),
                        demasia.getCantidad(),demasia.getCantidadNeta());
    }
    @Override
    public List<Demasia> numMovimiento(){
        return repository.list(PageRequest.of(0,1));
    }
    @Override
    public List<Demasia> listar(){
        return repository.listar();
    }

    @Override
    public List<Demasia> listByUni(String cod){
        return repository.lisByUni(cod);
    }
    @Override
    public List<Demasia> registrosFechaDemasia(String inicio, String fin, String cod) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date ini= format.parse(inicio.replace("T"," "));
        Date fn= format.parse(fin.replace("T"," "));
        return repository.filterFechaDemasiaHc(ini,fn,cod);
    }
    @Override
    public List<Demasia> listByProductDemasia(String cod_tipoHoja, String cod_uniOpe){
        return repository.listByProductDemasia(cod_tipoHoja,cod_uniOpe);
    }
    @Override
    public List<Demasia> registrosFechaDemasiaHc(String inicio, String fin, String cod, String codHc) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date ini= format.parse(inicio.replace("T"," "));
        Date fn= format.parse(fin.replace("T"," "));
        return repository.filterFechaDemasiaHc(ini,fn,cod,codHc);
    }


}
