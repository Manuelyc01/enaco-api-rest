package apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apirest.models.Inventario;
import apirest.models.Movimiento;
import apirest.models.Transferencia;
import apirest.repository.TransferenciaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {
    @Autowired
    private  final TransferenciaRepository repository;

    @Autowired
    private final MovimientoService movimientoService;
    @Autowired
    private final InventarioService inventarioService;

    public TransferenciaServiceImpl(TransferenciaRepository repository, MovimientoService movimientoService, InventarioService inventarioService) {
        this.repository = repository;
        this.movimientoService = movimientoService;
        this.inventarioService = inventarioService;
    }

    @Override
    @Transactional
    public String save(Transferencia transferencia){
        return repository.saveTransferencia(
                transferencia.getId_usuario().getId_usuario(),transferencia.getOrigen().getCod_uniOpe(),transferencia.getDestino().getCod_uniOpe(),
                transferencia.getTransportista(),transferencia.getPlacaVehiculo(),transferencia.getComentario(),transferencia.getCod_tipoHoja().getCod_tipoHoja(),
                transferencia.getCantidad()
        );
    }

    @Override
    public List<Transferencia> listByUni(String cod){
        return repository.lisByUni(cod);
    }
    @Override
    public List<Transferencia> listar(){
        return repository.listar();
    }
    @Override
    public List<Transferencia> registrosFechaTransferencia(String inicio, String fin, String cod) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date ini= format.parse(inicio.replace("T"," "));
        Date fn= format.parse(fin.replace("T"," "));
        return repository.filterFechaTransferenciaHc(ini,fn,cod);
    }
    @Override
    public List<Transferencia> listByProductTransferencia(String cod_tipoHoja, String cod_uniOpe){
        return repository.listByProductTransferencia(cod_tipoHoja,cod_uniOpe);
    }
    @Override
    public List<Transferencia> registrosFechaTransferenciaHc(String inicio, String fin, String cod, String codHc) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date ini= format.parse(inicio.replace("T"," "));
        Date fn= format.parse(fin.replace("T"," "));
        return repository.filterFechaTransferenciaHc(ini,fn,cod,codHc);
    }

}
