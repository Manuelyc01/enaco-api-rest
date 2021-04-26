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
    @Override
    public List<TipoHojaCoca> actaHojas(Integer periodo, String cod) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date año=new Date();
        int year = año.getYear();
        Date globalinicio=new Date(year,periodo,01);
        Date globalfin;
        if (periodo==11){
            globalfin=new Date(year,0,01);
        }else {
            globalfin=new Date(year,periodo+1,01);
        }

        Date ini =format.parse(format.format(globalinicio));
        Date fn =format.parse(format.format(globalfin));

        return inventarioRepository.actaHojas(ini,fn,cod);
    }
    @Override
    public Double actaSaldo(Integer periodo, String cod, String codHc) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date año=new Date();
        int year = año.getYear();
        Date globalinicio=new Date(year,periodo,01);
        Date globalfin;
        if (periodo==11){
            globalfin=new Date(year,0,01);
        }else {
            globalfin=new Date(year,periodo+1,01);
        }

        Date ini =format.parse(format.format(globalinicio));
        Date fn =format.parse(format.format(globalfin));
        List<Inventario> in = inventarioRepository.actaSaldo(ini, fn, cod, codHc, PageRequest.of(0, 1));
        if (in.size()==0){
            return 0.00;
        }else {
            return in.get(0).getStockInicial();
        }
    }
    @Override
    public List<Ingreso> actaIngreso(Integer periodo, String cod, String codHc) throws ParseException {
        List<Ingreso> ingresos=new ArrayList<>();
        for (int i=1;i<5;i++){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");

            Date año=new Date();
            int year = año.getYear();
            Date globalinicio=new Date(year,periodo,01);
            Date globalfin;
            if (periodo==11){
                globalfin=new Date(year,0,01);
            }else {
                globalfin=new Date(year,periodo+1,01);
            }

            Date ini =format.parse(format.format(globalinicio));
            Date fn =format.parse(format.format(globalfin));
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
    @Override
    public List<IngresoSalida> actaIngresoSalida(Integer periodo, String cod, String codHc) throws ParseException {
        List<IngresoSalida> ingresos=new ArrayList<>();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date año=new Date();
        int year = año.getYear();
        Date globalinicio=new Date(year,periodo,01);
        Date globalfin;
        if (periodo==11){
            globalfin=new Date(year,0,01);
        }else {
            globalfin=new Date(year,periodo+1,01);
        }

        Date ini =format.parse(format.format(globalinicio));
        Date fn =format.parse(format.format(globalfin));

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
}
