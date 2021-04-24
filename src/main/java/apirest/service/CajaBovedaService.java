package apirest.service;

import apirest.models.CajaBoveda;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

public interface CajaBovedaService {
    List<CajaBoveda> list();

    @Transactional
    void save(CajaBoveda cajaBoveda);

    Double getSaldoFinal(String cod_uni);

    List<CajaBoveda> listByUni(String cod);

    List<CajaBoveda> listByUniT(String cod, Integer t);

    List<CajaBoveda> registrosFechaCajaBoveda(String inicio, String fin, String cod) throws ParseException;

    List<CajaBoveda> registrosFechaCajaBovedaT(String inicio, String fin, String cod, Integer t) throws ParseException;
}
