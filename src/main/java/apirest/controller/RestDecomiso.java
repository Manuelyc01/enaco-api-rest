package apirest.controller;

import apirest.models.Compra;
import apirest.models.Decomiso;
import apirest.service.DecomisoService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/decomiso")
public class RestDecomiso {

    private final DecomisoService decomisoService;

    public RestDecomiso(DecomisoService decomisoService) {
        this.decomisoService = decomisoService;
    }
    @RequestMapping(method = RequestMethod.POST, value = "/registro")
    public String save(@RequestBody Decomiso decomiso){
        return decomisoService.save(decomiso);
    }

    @GetMapping("/numMoviento")
    public Integer numMoviento(){
        List<Decomiso> d = decomisoService.numMovimiento();
        return d.get(0).getId_decomiso();
    }
    @GetMapping("/listar")
    public List<Decomiso> listar(){
        return decomisoService.listar();
    }

    @GetMapping("/listByUni/{cod}")
    public List<Decomiso> listByUni(@PathVariable String cod){
        return decomisoService.listByUni(cod);
    }

    @GetMapping("/registrosFechaDecomiso/{inicio}/{fin}/{cod}")
    public List<Decomiso> registrosFechaDecomiso(@PathVariable String inicio, @PathVariable String fin, @PathVariable String cod) throws ParseException {
        return decomisoService.registrosFechaDecomiso(inicio, fin, cod);
    }
    @GetMapping("/listByProductDecomiso/{ch}/{cu}")
    public List<Decomiso> listByProductDecomiso(@PathVariable String ch,@PathVariable String cu){
        return decomisoService.listByProductDecomiso(ch,cu);
    }
    @GetMapping("/registrosFechaDecomisoHc/{inicio}/{fin}/{cod}/{codHc}")
    public List<Decomiso> registrosFechaDecomisoHc(@PathVariable String inicio, @PathVariable String fin, @PathVariable String cod, @PathVariable String codHc) throws ParseException {
        return decomisoService.registrosFechaDecomisoHc(inicio, fin, cod,codHc);
    }
}
