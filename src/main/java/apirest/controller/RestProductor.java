package apirest.controller;


import apirest.models.Decomiso;
import apirest.models.Productor;
import apirest.service.ProductorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/productor")
public class RestProductor {

    private final ProductorService productorService;

    public RestProductor(ProductorService productorService) {
        this.productorService = productorService;
    }
    @GetMapping("/listar")
    public List<Productor> listar(){
        return productorService.list();
    }

    @GetMapping("/findByCedula/{cedula}")
    public Productor findByCedula(@PathVariable String cedula){
        return productorService.findByCedula(cedula);
    }



}
