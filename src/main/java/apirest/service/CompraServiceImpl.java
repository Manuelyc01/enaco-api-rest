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
    public void save(Compra compra){
        //REGISTRO CAJA BOVEDA
        CajaBoveda cajaBoveda=new CajaBoveda();
        cajaBoveda.setId_usuario(compra.getId_usuario());
        cajaBoveda.setCod_uniOpe(compra.getCod_uniOpe());
        TipoTransaccion tipoT = tipoTransacService.getById(3);
        cajaBoveda.setId_tipoTransac(tipoT);
        cajaBoveda.setFecha(compra.getFecha());
        cajaBoveda.setMonto(compra.getTotalCompra());
        //EX
        UnidadOperativa uo = unidadOpeService.findByCod(cajaBoveda.getCod_uniOpe().getCod_uniOpe());//UNIDAD
        Integer id_tipo = cajaBoveda.getId_tipoTransac().getId_tipoTransac();//Tipo transac
        Double monto = cajaBoveda.getMonto();
        if(id_tipo==2 ||id_tipo==3 && uo.getCajaBoveda()<monto){
            throw new RuntimeException("Sin saldo caja boveda");
        }
        //REGISTRO INVENTARIO
        Inventario inventario=new Inventario();
        inventario.setId_usuario(compra.getId_usuario());
        inventario.setCod_almacen(compra.getCod_uniOpe());
        Movimiento mv = movimientoService.findById(1);
        inventario.setId_movimiento(mv);
        inventario.setDocumento(compra.getNum_liquidacion());
        inventario.setCod_tipoHoja(compra.getCod_tipoHoja());
        inventario.setPesoNeto(compra.getPesoNeto());
        //STOCK INICIAL--STOCK FINAL
        List<Inventario> inventarios = inventarioService.listByProductAlmacen(compra.getCod_tipoHoja().getCod_tipoHoja(), compra.getCod_uniOpe().getCod_uniOpe());
        if (inventarios.size()==0){
            inventario.setStockInicial(0.00);
            inventario.setStockFinal(+compra.getPesoNeto());
        }else {
            List<Inventario> inv1 = inventarioService.listByProductAlmacenOne(compra.getCod_tipoHoja().getCod_tipoHoja(), compra.getCod_uniOpe().getCod_uniOpe());
            Inventario inventario1 = inv1.get(0);
            inventario.setStockInicial(inventario1.getStockFinal());
            inventario.setStockFinal(inventario1.getStockFinal()+compra.getPesoNeto());
        }
        cajaBovedaService.save(cajaBoveda);
        usuarioService.compra(compra.getId_usuario().getId_usuario());
        inventarioService.save(inventario);
        compraRepository.save(compra);
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
