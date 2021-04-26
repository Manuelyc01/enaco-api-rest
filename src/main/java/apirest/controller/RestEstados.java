package apirest.controller;

import apirest.models.Decomiso;
import apirest.models.Estado;
import apirest.service.EstadoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class RestEstados {

    private final EstadoService estadoService;

    public RestEstados(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping("/listar")
    public List<Estado> listar(){
        return estadoService.estados();
    }

}
