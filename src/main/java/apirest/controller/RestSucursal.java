package apirest.controller;


import apirest.models.Decomiso;
import apirest.models.Sucursal;
import apirest.service.SucursalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sucursal")
public class RestSucursal {
    private final SucursalService sucursalService;

    public RestSucursal(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }
    @GetMapping("/listar")
    public List<Sucursal> listar(){
        return sucursalService.list();
    }

}
