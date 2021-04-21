package apirest.controller;

import apirest.models.CajaBoveda;
import apirest.models.Movimiento;
import apirest.service.MovimientoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movimiento")
public class RestMovimiento {
    private final MovimientoService movimientoService;

    public RestMovimiento(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }
    @GetMapping("/listar")
    public List<Movimiento> listar(){
        return movimientoService.list();
    }
    @GetMapping("/findById/{id}")
    public Movimiento findById(@PathVariable Integer id){
        return movimientoService.findById(id);
    }


}
