package apirest.controller;

import apirest.models.CajaBoveda;
import apirest.service.CajaBovedaService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/cajaBoveda")
public class RestCajaBoveda {

    private final CajaBovedaService cajaBovedaService;

    public RestCajaBoveda(CajaBovedaService cajaBovedaService) {
        this.cajaBovedaService = cajaBovedaService;
    }

    @GetMapping("/listar")
    public List<CajaBoveda> listar(){
        return cajaBovedaService.list();
    }
    @RequestMapping(method = RequestMethod.POST, value = "/registro")
    public void save(@RequestBody CajaBoveda cajaBoveda){
        cajaBovedaService.save(cajaBoveda);
    }
    @GetMapping("/listByUni/{cod}")
    public List<CajaBoveda> listByUni(@PathVariable String cod){
        return cajaBovedaService.listByUni(cod);
    }
    @GetMapping("/listByUniT/{cod}/{id}")
    public List<CajaBoveda> listByUniT(@PathVariable String cod,@PathVariable Integer id){
        return cajaBovedaService.listByUniT(cod,id);
    }
    @GetMapping("/registrosFechaCajaBoveda/{inicio}/{fin}/{cod}")
    public List<CajaBoveda> registrosFechaCajaBoveda(@PathVariable String inicio, @PathVariable String fin, @PathVariable String cod) throws ParseException {
        return cajaBovedaService.registrosFechaCajaBoveda(inicio, fin, cod);
    }

    @GetMapping("/registrosFechaCajaBoveda/{inicio}/{fin}/{cod}/{t}")
    public List<CajaBoveda> registrosFechaCajaBovedaT(@PathVariable String inicio, @PathVariable String fin, @PathVariable String cod, @PathVariable Integer t) throws ParseException {
        return cajaBovedaService.registrosFechaCajaBovedaT(inicio, fin, cod,t);
    }


}
