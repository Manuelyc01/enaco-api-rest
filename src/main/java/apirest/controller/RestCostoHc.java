package apirest.controller;

import apirest.models.CajaBoveda;
import apirest.models.CostoHojaCoca;
import apirest.service.CostoHcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/costoHc")
public class RestCostoHc {

    private final CostoHcService costoHcService;

    public RestCostoHc(CostoHcService costoHcService) {
        this.costoHcService = costoHcService;
    }
    @GetMapping("/listar")
    public List<CostoHojaCoca> listar(){
        return costoHcService.list();
    }

    @GetMapping("/filterCostoHc/{cod}")
    public List<CostoHojaCoca> listByUni(@PathVariable String cod){
        return costoHcService.filterCostoHc(cod);
    }

}
