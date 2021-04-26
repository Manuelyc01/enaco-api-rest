package apirest.controller;

import apirest.models.Compra;
import apirest.models.Inventario;
import apirest.service.CompraService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/compra")
public class RestCompra {

    private final CompraService compraService;

    public RestCompra(CompraService compraService) {
        this.compraService = compraService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registro")
    public String save(@RequestBody Compra compra){
        return compraService.save(compra);
    }

    @GetMapping("/findById/{id}")
    public Compra findById(@PathVariable Integer id){
        return compraService.findById(id);
    }

    @GetMapping("/listar")
    public List<Compra> listar(){
        return compraService.list();
    }

    @GetMapping("/listByIdUsuario/{id}")
    public List<Compra> listByIdUsuario(@PathVariable Integer id){
        return compraService.listByIdUsuario(id);
    }

    @GetMapping("/listByUni/{cod}")
    public List<Compra> listByUni(@PathVariable String cod){
        return compraService.listByUni(cod);
    }

    @GetMapping("/registrosFechaCompra/{inicio}/{fin}/{cod}")
    public List<Compra> registrosFechaCompra(@PathVariable String inicio, @PathVariable String fin, @PathVariable String cod) throws ParseException {
        return compraService.registrosFechaCompra(inicio, fin, cod);
    }
    @GetMapping("/listByProductCompra/{ch}/{cu}")
    public List<Compra> listByProductCompra(@PathVariable String ch,@PathVariable String cu){
        return compraService.listByProductCompra(ch,cu);
    }
    @GetMapping("/registrosFechaCompraHc/{inicio}/{fin}/{cod}/{codHc}")
    public List<Compra> registrosFechaCompraHc(@PathVariable String inicio, @PathVariable String fin, @PathVariable String cod, @PathVariable String codHc) throws ParseException {
        return compraService.registrosFechaCompraHc(inicio, fin, cod,codHc);
    }






}
