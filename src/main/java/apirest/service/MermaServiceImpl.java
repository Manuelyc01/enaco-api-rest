package apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import apirest.models.Inventario;
import apirest.models.Merma;
import apirest.models.Movimiento;
import apirest.repository.MermaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MermaServiceImpl implements MermaService{

    @Autowired
    private final MermaRepository repository;

    @Autowired
    private final MovimientoService movimientoService;
    @Autowired
    private final InventarioService inventarioService;

    public MermaServiceImpl(MermaRepository repository, MovimientoService movimientoService, InventarioService inventarioService) {
        this.repository = repository;
        this.movimientoService = movimientoService;
        this.inventarioService = inventarioService;
    }
    @Override
    public List<Merma> numMovimiento(){
        return repository.list(PageRequest.of(0,1));
    }
    @Override
    public List<Merma> listar(){
        return repository.listar();
    }

    @Override
    @Transactional
    public String save(Merma merma){
        return
                repository.saveMerma(merma.getId_usuario().getId_usuario(),merma.getCod_uniOpe().getCod_uniOpe(),merma.getComentario(),merma.getCod_tipoHoja().getCod_tipoHoja(),
                        merma.getCantidad(),merma.getCantidadNeta());
    }

    @Override
    public List<Merma> listByUni(String cod){
        return repository.lisByUni(cod);
    }
    @Override
    public List<Merma> registrosFechaMerma(String inicio, String fin, String cod) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date ini= format.parse(inicio.replace("T"," "));
        Date fn= format.parse(fin.replace("T"," "));
        return repository.filterFechaMermaHc(ini,fn,cod);
    }
    @Override
    public List<Merma> listByProductMerma(String cod_tipoHoja, String cod_uniOpe){
        return repository.listByProductMerma(cod_tipoHoja,cod_uniOpe);
    }
    @Override
    public List<Merma> registrosFechaMermaHc(String inicio, String fin, String cod, String codHc) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date ini= format.parse(inicio.replace("T"," "));
        Date fn= format.parse(fin.replace("T"," "));
        return repository.filterFechaMermaHc(ini,fn,cod,codHc);
    }

}
