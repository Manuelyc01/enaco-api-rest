package apirest.controller;


import apirest.models.Decomiso;
import apirest.models.TipoHojaCoca;
import apirest.service.TipoHcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tipoHc")
public class RestTipoHc {
    private final TipoHcService tipoHcService;

    public RestTipoHc(TipoHcService tipoHcService) {
        this.tipoHcService = tipoHcService;
    }

    @GetMapping("/listar")
    public List<TipoHojaCoca> listar(){
        return tipoHcService.list();
    }

}
