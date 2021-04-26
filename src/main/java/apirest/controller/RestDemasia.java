package apirest.controller;

import apirest.models.Decomiso;
import apirest.models.Demasia;
import apirest.service.DemasiaService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/demasia")
public class RestDemasia {
    private final DemasiaService demasiaService;

    public RestDemasia(DemasiaService demasiaService) {
        this.demasiaService = demasiaService;
    }
    @RequestMapping(method = RequestMethod.POST, value = "/registro")
    public String save(@RequestBody Demasia demasia){
        return demasiaService.save(demasia);
    }

    @GetMapping("/numMoviento")
    public Integer numMoviento(){
        List<Demasia> d = demasiaService.numMovimiento();
        return d.get(0).getId_demasia();
    }

    @GetMapping("/listar")
    public List<Demasia> listar(){
        return demasiaService.listar();
    }

    @GetMapping("/listByUni/{cod}")
    public List<Demasia> listByUni(@PathVariable String cod){
        return demasiaService.listByUni(cod);
    }

    @GetMapping("/registrosFechaDemasia/{inicio}/{fin}/{cod}")
    public List<Demasia> registrosFechaDemasia(@PathVariable String inicio, @PathVariable String fin, @PathVariable String cod) throws ParseException {
        return demasiaService.registrosFechaDemasia(inicio, fin, cod);
    }
    @GetMapping("/listByProductDemasia/{ch}/{cu}")
    public List<Demasia> listByProductDemasia(@PathVariable String ch,@PathVariable String cu){
        return demasiaService.listByProductDemasia(ch,cu);
    }
    @GetMapping("/registrosFechaDemasiaHc/{inicio}/{fin}/{cod}/{codHc}")
    public List<Demasia> registrosFechaDemasiaHc(@PathVariable String inicio, @PathVariable String fin, @PathVariable String cod, @PathVariable String codHc) throws ParseException {
        return demasiaService.registrosFechaDemasiaHc(inicio, fin, cod,codHc);
    }

}
