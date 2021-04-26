package apirest.service;

import apirest.models.*;
import apirest.repository.CompraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CompraServiceImpl implements CompraService{

    private final CompraRepository compraRepository;
    private final UsuarioService usuarioService;
    private final UnidadOpeService unidadOpeService;
    private final RepresentanteService representanteService;
    private final TipoTransacService tipoTransacService;
    private final CajaBovedaService cajaBovedaService;
    private final MovimientoService movimientoService;
    private final InventarioService inventarioService;

    public CompraServiceImpl(CompraRepository compraRepository, UsuarioService usuarioService, UnidadOpeService unidadOpeService, RepresentanteService representanteService, TipoTransacService tipoTransacService, CajaBovedaService cajaBovedaService, MovimientoService movimientoService, InventarioService inventarioService) {
        this.compraRepository = compraRepository;
        this.usuarioService = usuarioService;
        this.unidadOpeService = unidadOpeService;
        this.representanteService = representanteService;
        this.tipoTransacService = tipoTransacService;
        this.cajaBovedaService = cajaBovedaService;
        this.movimientoService = movimientoService;
        this.inventarioService = inventarioService;
    }

    @Override
    @Transactional
    public String save(Compra compra){

        UnidadOperativa uo = unidadOpeService.findByCod(compra.getCod_uniOpe().getCod_uniOpe());//UNIDAD
        if(uo.getCajaBoveda()<compra.getTotalCompra()){
            throw new RuntimeException("Sin saldo caja boveda");
        }
        return compraRepository.saveCompra(compra.getNum_liquidacion(),compra.getCedula_productor().getCedula(),compra.getDni_repre(),
                compra.getCod_uniOpe().getCod_uniOpe(),compra.getCod_tipoHoja().getCod_tipoHoja(),compra.getPesoBruto(),compra.getTara(),
                compra.getHumedad(),compra.getPesoNeto(),compra.getValorCompra(),compra.getIgv(),compra.getTotalCompra(),
                compra.getSon(),compra.getId_usuario().getId_usuario(),compra.getId_repre().getId_representante());
    }
    @Override
    public List<Compra> list(){
        return compraRepository.list();
    }
    @Override
    public List<Compra> listByIdUsuario(Integer id){
        return compraRepository.listCompraByIdUsuario(id);
    }
    @Override
    public Compra findById(Integer id){
        return  compraRepository.getOne(id);
    }
    @Override
    public List<Compra> listByUni(String cod){
        return compraRepository.lisByUni(cod);
    }
    @Override
    public List<Compra> registrosFechaCompra(String inicio, String fin, String cod) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date ini= format.parse(inicio.replace("T"," "));
        Date fn= format.parse(fin.replace("T"," "));
        return compraRepository.filterFechaCompraHc(ini,fn,cod);
    }
    @Override
    public List<Compra> listByProductCompra(String cod_tipoHoja, String cod_uniOpe){
        return compraRepository.listByProductCompra(cod_tipoHoja,cod_uniOpe);
    }
    @Override
    public List<Compra> registrosFechaCompraHc(String inicio, String fin, String cod, String codHc) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date ini= format.parse(inicio.replace("T"," "));
        Date fn= format.parse(fin.replace("T"," "));
        return compraRepository.filterFechaCompraHc(ini,fn,cod,codHc);
    }

}
