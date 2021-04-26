package apirest.controller;

import apirest.models.Decomiso;
import apirest.models.Transferencia;
import apirest.service.TransferenciaService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/transferencia")
public class RestTransferencia {

    private final TransferenciaService transferenciaService;

    public RestTransferencia(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registro")
    public String save(@RequestBody Transferencia transferencia){
        return transferenciaService.save(transferencia);
    }

    @GetMapping("/listar")
    public List<Transferencia> listar(){
        return transferenciaService.listar();
    }

    @GetMapping("/listByUni/{cod}")
    public List<Transferencia> listByUni(@PathVariable String cod){
        return transferenciaService.listByUni(cod);
    }

    @GetMapping("/registrosFechaTransferencia/{inicio}/{fin}/{cod}")
    public List<Transferencia> registrosFechaTransferencia(@PathVariable String inicio, @PathVariable String fin, @PathVariable String cod) throws ParseException {
        return transferenciaService.registrosFechaTransferencia(inicio, fin, cod);
    }
    @GetMapping("/listByProductTransferencia/{ch}/{cu}")
    public List<Transferencia> listByProductTransferencia(@PathVariable String ch,@PathVariable String cu){
        return transferenciaService.listByProductTransferencia(ch,cu);
    }
    @GetMapping("/registrosFechaTransferenciaHc/{inicio}/{fin}/{cod}/{codHc}")
    public List<Transferencia> registrosFechaTransferenciaHc(@PathVariable String inicio, @PathVariable String fin, @PathVariable String cod, @PathVariable String codHc) throws ParseException {
        return transferenciaService.registrosFechaTransferenciaHc(inicio, fin, cod,codHc);
    }
}
