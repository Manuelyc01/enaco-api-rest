package apirest.service;

import apirest.models.UnidadOperativa;
import apirest.repository.UnidadOperativaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UnidadOpeServiceImpl implements UnidadOpeService{

    private final UnidadOperativaRepository unidadOperativaRepository;

    public UnidadOpeServiceImpl(UnidadOperativaRepository unidadOperativaRepository) {
        this.unidadOperativaRepository = unidadOperativaRepository;
    }

    @Override
    public List<UnidadOperativa> listar(){
        return unidadOperativaRepository.findAll();
    }
    @Override
    public UnidadOperativa findByCod(String cod){
        return unidadOperativaRepository.getOne(cod);
    }
    @Override
    public void save(UnidadOperativa unidadOperativa){
        unidadOperativaRepository.save(unidadOperativa);
    }

    @Override
    public void saveCajaBoveda(String cod, double monto,Integer tipo){
        UnidadOperativa u = unidadOperativaRepository.getOne(cod);
        Double m=Math.round(monto * 100.0) / 100.0;
        if(tipo ==1){
            if(u.getCajaBoveda()==null){
                u.setCajaBoveda(+m);
            } else if (u.getCajaBoveda() >= 0){
                u.setCajaBoveda(u.getCajaBoveda()+m);
            }
        }else if (tipo==2){
            if(u.getCajaBoveda()>0){
                u.setCajaBoveda(Math.round((u.getCajaBoveda()-m) * 100.0) / 100.0);
            }
        }
        unidadOperativaRepository.save(u);
    }
}
