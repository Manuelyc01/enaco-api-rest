package apirest.controller;

import apirest.models.TipoTransaccion;
import apirest.service.TipoTransacService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tipoTransac")
public class RestTipoTransac {

    private final TipoTransacService tipoTransacService;

    public RestTipoTransac(TipoTransacService tipoTransacService) {
        this.tipoTransacService = tipoTransacService;
    }

    @GetMapping("/listar")
    public List<TipoTransaccion> listar(){
        return tipoTransacService.list();
    }
    @GetMapping("/getById/{id}")
    public TipoTransaccion getById(@PathVariable Integer id){
        return tipoTransacService.getById(id);
    }

}
