package apirest.controller;

import apirest.models.CajaBoveda;
import apirest.models.UnidadOperativa;
import apirest.service.UnidadOpeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidadOpe")
public class RestUnidadOperativa {

    private final UnidadOpeService unidadOpeService;

    public RestUnidadOperativa(UnidadOpeService unidadOpeService) {
        this.unidadOpeService = unidadOpeService;
    }

    @GetMapping("/listar")
    public List<UnidadOperativa> listar(){
        return unidadOpeService.listar();
    }
    @GetMapping("/findByCod/{cod}")
    public UnidadOperativa listByUni(@PathVariable String cod){
        return unidadOpeService.findByCod(cod);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/registro")
    public void save(@RequestBody UnidadOperativa unidadOperativa){
        unidadOpeService.save(unidadOperativa);
    }
    @GetMapping("/saveCajaBoveda/{cod}/{monto}/{tipo}")
    public void saveCajaBoveda(@PathVariable String cod, @PathVariable Double monto, @PathVariable Integer tipo){
        unidadOpeService.saveCajaBoveda(cod,monto,tipo);
    }
    @GetMapping("/listarAgeSu/{cod_agencia}/{cod_sucursal}")
    public List<UnidadOperativa> listarAgeSu(@PathVariable String cod_agencia, @PathVariable String cod_sucursal){
        return unidadOpeService.listarAgeSu(cod_agencia,cod_sucursal);
    }
    @GetMapping("/listarAgeSu/{cod_agencia}")
    public List<UnidadOperativa> listarAgencia(@PathVariable String cod_agencia){
        return unidadOpeService.listarAgencia(cod_agencia);
    }
    @GetMapping("/listarAgeSu/{cod_sucursal}")
    public List<UnidadOperativa> listarSucursal( @PathVariable String cod_sucursal){
        return unidadOpeService.listarSucursal(cod_sucursal);
    }




}
