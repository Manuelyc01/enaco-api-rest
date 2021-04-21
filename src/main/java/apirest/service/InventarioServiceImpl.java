package apirest.service;

import apirest.models.*;
import apirest.repository.InventarioRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class InventarioServiceImpl implements InventarioService{

    private final InventarioRepository inventarioRepository;
    private final UnidadOpeService unidadOpeService;

    public InventarioServiceImpl(InventarioRepository inventarioRepository, UnidadOpeService unidadOpeService) {
        this.inventarioRepository = inventarioRepository;
        this.unidadOpeService = unidadOpeService;
    }
    @Override
    public List<ActaRegistro> reporteActaRegistro(String cod, Integer per, String nombreUsuario) throws ParseException {
        Reporte reporte=new Reporte();
        UnidadOperativa uni = unidadOpeService.findByCod(cod);
        reporte.setCodUni(uni);
        reporte.setPeriodo(per);
        List<ActaRegistro> actaRegistros=new ArrayList<>();

        List<TipoHojaCoca> tipoHojaCocas = actaHojas(reporte.getPeriodo(), reporte.getCodUni().getCod_uniOpe());//HOJAS DE COCA
        List<Ingreso> ingresos;
        List<IngresoSalida> ingresoSalidas;
        for (int i=0;i<tipoHojaCocas.size();i++){
            Double s = actaSaldo(reporte.getPeriodo(), reporte.getCodUni().getCod_uniOpe(), tipoHojaCocas.get(i).getCod_tipoHoja());
            ingresos= actaIngreso(reporte.getPeriodo(), reporte.getCodUni().getCod_uniOpe(), tipoHojaCocas.get(i).getCod_tipoHoja());//INGRESOS(1,2,3) SALIDA(4)
            ingresoSalidas= actaIngresoSalida(reporte.getPeriodo(), reporte.getCodUni().getCod_uniOpe(), tipoHojaCocas.get(i).getCod_tipoHoja());
            ActaRegistro actaRegistro= new ActaRegistro();
            actaRegistro.setCodHc(tipoHojaCocas.get(i).getCod_tipoHoja());//CODHC
            actaRegistro.setSaldoMesAnterior(s);//SALDO
            actaRegistro.setIngresoCompra(ingresos.get(0).getMonto());//COMPRAS
            actaRegistro.setIngresoDecomiso(ingresos.get(1).getMonto());//DECOMISO
            actaRegistro.setIngresoDemasia(ingresos.get(2).getMonto());//DEMASIA
            actaRegistro.setSalidaMerma(ingresos.get(3).getMonto());//SALIDA MERMA

            actaRegistro.setIngresoTransferencia(ingresoSalidas.get(0).getMonto());//INGRESO TRANSF
            actaRegistro.setSalidaTransferencia(ingresoSalidas.get(1).getMonto());//SALIDA TRANSF

            actaRegistros.add(actaRegistro);
        }
        for (int i=0;i<actaRegistros.size();i++){
            ActaRegistro ar = actaRegistros.get(i);
            ar.setSubtotalIngreso(ar.getIngresoCompra()+ar.getIngresoDecomiso()+ar.getIngresoDemasia()+ar.getIngresoTransferencia());//SUB INGRESO
            ar.setSubtotalSalida(ar.getSalidaMerma()+ar.getSalidaTransferencia());//SUB SALIDA

            ar.setTotal(ar.getSaldoMesAnterior()+ar.getSubtotalIngreso()-ar.getSubtotalSalida());//TOTAL
        }

        Date a=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        String year = formatter.format(a);

        String mes="";
        switch (reporte.getPeriodo()){
            case 0:
                mes="Enero";
                break;
            case 1:
                mes="Febrero";
                break;
            case 2:
                mes="Marzo";
                break;
            case 3:
                mes="Abril";
                break;
            case 4:
                mes="Mayo";
                break;
            case 5:
                mes="Junio";
                break;
            case 6:
                mes="Julio";
                break;
            case 7:
                mes="Agosto";
                break;
            case 8:
                mes="Septiembre";
                break;
            case 9:
                mes="Octubre";
                break;
            case 10:
                mes="Noviembre";
                break;
            case 11:
                mes="Diciembre";
                break;
        }

        if (actaRegistros.size()>0){
            actaRegistros.get(0).setUnidadOpe(reporte.getCodUni().getNom_uniOpe());
            actaRegistros.get(0).setUsuario(nombreUsuario);
            actaRegistros.get(0).setFechaInicio(mes);
            actaRegistros.get(0).setFechaFin(year);
        }
        return actaRegistros;
    }

    @Override
    public void save(Inventario inventario){
        inventarioRepository.save(inventario);
    }

    @Override
    public List<Inventario> list(){
        return inventarioRepository.list();
    }

    @Override
    public List<Inventario> listByProductAlmacen(String cod_tipoHoja, String cod_uniOpe){
        return inventarioRepository.listByProductAlmacen(cod_tipoHoja,cod_uniOpe);
    }
    @Override
    public List<Inventario> registrosFechaAlmacen(String inicio, String fin, String cod) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date ini= format.parse(inicio.replace("T"," "));
        Date fn= format.parse(fin.replace("T"," "));
        return inventarioRepository.filterFechaAlmacen(ini,fn,cod);
    }
    @Override
    public List<Inventario> registrosFechaAlmacenHc(String inicio, String fin, String cod, String codHc) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date ini= format.parse(inicio.replace("T"," "));
        Date fn= format.parse(fin.replace("T"," "));
        return inventarioRepository.filterFechaAlmacenHc(ini,fn,cod,codHc);
    }
    @Override
    public List<Inventario> listByProductAlmacenOne(String cod_tipoHoja, String cod_uniOpe){
        return inventarioRepository.listByProductAlmacenOne(cod_tipoHoja,cod_uniOpe, PageRequest.of(0,1));
    }
    @Override
    public List<Inventario> stockHcAlmacen(String cod_tipoHoja, String cod_uniOpe){
        return inventarioRepository.stockHcAlmacen(cod_tipoHoja,cod_uniOpe,PageRequest.of(0,1));
    }
    @Override
    public List<Inventario> listByUni(String cod_uniOpe){
        return inventarioRepository.listByUni(cod_uniOpe);
    }

    //ACTA DE INVERTARIO
    public List<TipoHojaCoca> actaHojas(Integer periodo, String cod) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date ini =format.parse(format.format(globalInicio(periodo)));
        Date fn =format.parse(format.format(globalFin(periodo)));

        return inventarioRepository.actaHojas(ini,fn,cod);
    }
    public Double actaSaldo(Integer periodo, String cod, String codHc) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date ini =format.parse(format.format(globalInicio(periodo)));
        Date fn =format.parse(format.format(globalFin(periodo)));
        List<Inventario> in = inventarioRepository.actaSaldo(ini, fn, cod, codHc, PageRequest.of(0, 1));
        if (in.size()==0){
            return 0.00;
        }else {
            return in.get(0).getStockInicial();
        }
    }
    public List<Ingreso> actaIngreso(Integer periodo, String cod, String codHc) throws ParseException {
        List<Ingreso> ingresos=new ArrayList<>();
        for (int i=1;i<5;i++){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");

            Date ini =format.parse(format.format(globalInicio(periodo)));
            Date fn =format.parse(format.format(globalFin(periodo)));
            List<Double> d = inventarioRepository.actaIngreso(ini, fn, cod, codHc,i, PageRequest.of(0, 1));
            Ingreso ingreso=new Ingreso();
            ingreso.setId(i);
            if (d.get(0)==null){
                ingreso.setMonto(0.00);
            }else {
                ingreso.setMonto(d.get(0));
            }
            ingresos.add(ingreso);
        }
        return ingresos;
    }
    public List<IngresoSalida> actaIngresoSalida(Integer periodo, String cod, String codHc) throws ParseException {
        List<IngresoSalida> ingresos=new ArrayList<>();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date ini =format.parse(format.format(globalInicio(periodo)));
        Date fn =format.parse(format.format(globalFin(periodo)));

        List<Double> i = inventarioRepository.actaIngresoTransferencia(ini, fn, cod, codHc, PageRequest.of(0, 1));
        List<Double> s = inventarioRepository.actaSalidaTransferencia(ini, fn, cod, codHc, PageRequest.of(0, 1));

        IngresoSalida ingreso=new IngresoSalida();
        ingreso.setId(1);
        if (i.get(0)==null){
            ingreso.setMonto(0.00);
        }else {
            ingreso.setMonto(i.get(0));
        }
        IngresoSalida ingreso2=new IngresoSalida();
        ingreso2.setId(2);
        if (s.get(0)==null){
            ingreso2.setMonto(0.00);
        }else {
            ingreso2.setMonto(s.get(0));
        }
        ingresos.add(ingreso);
        ingresos.add(ingreso2);
        return ingresos;
    }
    //inicio de mes
    public String globalInicio(Integer periodo){
        Date año=new Date();
        int year = año.getYear();
        Date globalinicio=new Date(year,periodo,01);
        return globalinicio.toString();
    }
    //fin de mes
    public String globalFin(Integer periodo){
        Date año=new Date();
        int year = año.getYear();
        Date globalfin;
        if (periodo==11){
            globalfin=new Date(year,0,01);
        }else {
            globalfin=new Date(year,periodo+1,01);
        }
        return globalfin.toString();
    }
}
