package apirest.service;

import apirest.models.Demasia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

public interface DemasiaService {
    @Transactional
    String save(Demasia demasia);

    List<Demasia> numMovimiento();

    List<Demasia> listar();

    List<Demasia> listByUni(String cod);

    List<Demasia> registrosFechaDemasia(String inicio, String fin, String cod) throws ParseException;

    List<Demasia> listByProductDemasia(String cod_tipoHoja, String cod_uniOpe);

    List<Demasia> registrosFechaDemasiaHc(String inicio, String fin, String cod, String codHc) throws ParseException;


}
