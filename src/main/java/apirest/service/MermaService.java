package apirest.service;

import apirest.models.Merma;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

public interface MermaService {
    List<Merma> numMovimiento();

    List<Merma> listar();

    @Transactional
    String save(Merma merma);

    List<Merma> listByUni(String cod);

    List<Merma> registrosFechaMerma(String inicio, String fin, String cod) throws ParseException;

    List<Merma> listByProductMerma(String cod_tipoHoja, String cod_uniOpe);

    List<Merma> registrosFechaMermaHc(String inicio, String fin, String cod, String codHc) throws ParseException;
}
