package apirest.controller;

import apirest.models.Representante;
import apirest.service.RepresentanteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/representante")
public class RestRepresentante {

    private final RepresentanteService representanteService;

    public RestRepresentante(RepresentanteService representanteService) {
        this.representanteService = representanteService;
    }

    @GetMapping("/findByDni/{dni}")
    public Representante findByDni(@PathVariable String dni){
        return representanteService.findByDni(dni);
    }
    @GetMapping("/findById/{id}")
    public Representante findById(@PathVariable Integer id){
        return representanteService.findById(id);
    }

}
