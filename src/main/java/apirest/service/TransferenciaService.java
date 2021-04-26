package apirest.service;

import apirest.models.Transferencia;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

public interface TransferenciaService {

    @Transactional
    String save(Transferencia transferencia);


    List<Transferencia> listByUni(String cod);

    List<Transferencia> listar();

    List<Transferencia> registrosFechaTransferencia(String inicio, String fin, String cod) throws ParseException;

    List<Transferencia> listByProductTransferencia(String cod_tipoHoja, String cod_uniOpe);

    List<Transferencia> registrosFechaTransferenciaHc(String inicio, String fin, String cod, String codHc) throws ParseException;
}
