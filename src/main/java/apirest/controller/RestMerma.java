package apirest.controller;


import apirest.models.Decomiso;
import apirest.models.Merma;
import apirest.service.MermaService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/merma")
public class RestMerma {

    private final MermaService mermaService;

    public RestMerma(MermaService mermaService) {
        this.mermaService = mermaService;
    }
    @RequestMapping(method = RequestMethod.POST, value = "/registro")
    public String save(@RequestBody Merma merma){
        return mermaService.save(merma);
    }

    @GetMapping("/numMoviento")
    public Integer numMoviento(){
        List<Merma> d = mermaService.numMovimiento();
        return d.get(0).getId_merma();
    }
    @GetMapping("/listar")
    public List<Merma> listar(){
        return mermaService.listar();
    }

    @GetMapping("/listByUni/{cod}")
    public List<Merma> listByUni(@PathVariable String cod){
        return mermaService.listByUni(cod);
    }

    @GetMapping("/registrosFechaMerma/{inicio}/{fin}/{cod}")
    public List<Merma> registrosFechaMerma(@PathVariable String inicio, @PathVariable String fin, @PathVariable String cod) throws ParseException {
        return mermaService.registrosFechaMerma(inicio, fin, cod);
    }
    @GetMapping("/listByProductMerma/{ch}/{cu}")
    public List<Merma> listByProductMerma(@PathVariable String ch,@PathVariable String cu){
        return mermaService.listByProductMerma(ch,cu);
    }
    @GetMapping("/registrosFechaMermaHc/{inicio}/{fin}/{cod}/{codHc}")
    public List<Merma> registrosFechaMermaHc(@PathVariable String inicio, @PathVariable String fin, @PathVariable String cod, @PathVariable String codHc) throws ParseException {
        return mermaService.registrosFechaMermaHc(inicio, fin, cod,codHc);
    }

}
