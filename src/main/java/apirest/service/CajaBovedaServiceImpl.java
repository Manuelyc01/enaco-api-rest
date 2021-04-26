package apirest.service;

import apirest.models.CajaBoveda;
import apirest.models.UnidadOperativa;
import apirest.repository.CajaBovedaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CajaBovedaServiceImpl implements CajaBovedaService{

    private final CajaBovedaRepository cajaBovedaRepository;
    private final UnidadOpeService unidadOpeService;

    public CajaBovedaServiceImpl(CajaBovedaRepository cajaBovedaRepository, UnidadOpeService unidadOpeService) {
        this.cajaBovedaRepository = cajaBovedaRepository;
        this.unidadOpeService = unidadOpeService;
    }

    @Override
    public List<CajaBoveda> list(){
        return cajaBovedaRepository.list();
    }

    @Override
    @Transactional
    public void save(CajaBoveda cajaBoveda){

        UnidadOperativa uo = unidadOpeService.findByCod(cajaBoveda.getCod_uniOpe().getCod_uniOpe());//UNIDAD
        Integer id_tipo = cajaBoveda.getId_tipoTransac().getId_tipoTransac();//Tipo transac
        Double monto = cajaBoveda.getMonto();
        if(id_tipo==2 ||id_tipo==3 && uo.getCajaBoveda()<monto){
                throw new RuntimeException("Sin saldo caja boveda");
        }
        cajaBovedaRepository.saveCajaBoveda(cajaBoveda.getId_usuario().getId_usuario(),cajaBoveda.getCod_uniOpe().getCod_uniOpe(),cajaBoveda.getId_tipoTransac().getId_tipoTransac(),
                cajaBoveda.getMonto());
    }
    @Override
    public Double getSaldoFinal(String cod_uni){
        List<CajaBoveda> saldoFinal = cajaBovedaRepository.getStockFinal(cod_uni, PageRequest.of(0, 1));
        return saldoFinal.get(0).getSaldoFinal();
    }

    @Override
    public List<CajaBoveda> listByUni(String cod){
        return cajaBovedaRepository.lisByUni(cod);
    }

    @Override
    public List<CajaBoveda> listByUniT(String cod, Integer t){
        return cajaBovedaRepository.lisByUniT(cod,t);
    }

    @Override
    public List<CajaBoveda> registrosFechaCajaBoveda(String inicio, String fin, String cod) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date ini= format.parse(inicio.replace("T"," "));
        Date fn= format.parse(fin.replace("T"," "));
        return cajaBovedaRepository.filterFechaCajaBoveda(ini,fn,cod);
    }
    @Override
    public List<CajaBoveda> registrosFechaCajaBovedaT(String inicio, String fin, String cod,Integer t) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date ini= format.parse(inicio.replace("T"," "));
        Date fn= format.parse(fin.replace("T"," "));
        return cajaBovedaRepository.filterFechaCajaBovedaT(ini,fn,cod,t);
    }
}
