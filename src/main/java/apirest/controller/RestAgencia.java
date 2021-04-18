package apirest.controller;

import apirest.models.Agencia;
import apirest.service.AgenciaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/agencia")
public class RestAgencia {

    private final AgenciaService agenciaService;

    public RestAgencia(AgenciaService agenciaService) {
        this.agenciaService = agenciaService;
    }
    @GetMapping("/listar")
    public List<Agencia> listar(){
        return agenciaService.list();
    }


}
