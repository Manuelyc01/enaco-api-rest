package apirest.controller;

import apirest.models.*;
import apirest.service.InventarioService;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class RestInventario {

    private final InventarioService inventarioService;

    public RestInventario(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping("/listar")
    public List<Inventario> listar(){
        return inventarioService.list();
    }
    @GetMapping("/listByUni/{cod}")
    public List<Inventario> listByUni(@PathVariable String cod){
        return inventarioService.listByUni(cod);
    }
    @GetMapping("/listByProductAlmacen/{ch}/{cu}")
    public List<Inventario> listByProductAlmacen(@PathVariable String ch,@PathVariable String cu){
        return inventarioService.listByProductAlmacen(ch,cu);
    }
    @GetMapping("/registrosFechaAlmacen/{inicio}/{fin}/{cod}")
    public List<Inventario> registrosFechaAlmacen(@PathVariable String inicio, @PathVariable String fin, @PathVariable String cod) throws ParseException {
        return inventarioService.registrosFechaAlmacen(inicio, fin, cod);
    }
    @GetMapping("/registrosFechaAlmacenHc/{inicio}/{fin}/{cod}/{codHc}")
    public List<Inventario> registrosFechaAlmacenHc(@PathVariable String inicio, @PathVariable String fin, @PathVariable String cod, @PathVariable String codHc) throws ParseException {
        return inventarioService.registrosFechaAlmacenHc(inicio, fin, cod,codHc);
    }
    @GetMapping("/listByProductAlmacenOne/{ch}/{cu}")
    public List<Inventario> listByProductAlmacenOne(@PathVariable String ch,@PathVariable String cu){
        return inventarioService.listByProductAlmacenOne(ch,cu);
    }
        @GetMapping("/stockHcAlmacen/{ch}/{cu}")
    public List<Inventario> stockHcAlmacen(@PathVariable String ch,@PathVariable String cu){
        return inventarioService.stockHcAlmacen(ch,cu);
    }
    @GetMapping("/actaHojas/{per}/{cod}")
    public List<TipoHojaCoca> actaHojas(@PathVariable Integer per,@PathVariable String cod) throws ParseException {
        return inventarioService.actaHojas(per,cod);
    }
    @GetMapping("/actaSaldo/{per}/{cod}/{codHc}")
    public Double actaSaldo(@PathVariable String cod, @PathVariable Integer per, @PathVariable String codHc) throws ParseException {
        return inventarioService.actaSaldo(per,cod,codHc);
    }
    @GetMapping("/actaIngreso/{per}/{cod}/{codHc}")
    public List<Ingreso> actaIngreso(@PathVariable String cod, @PathVariable Integer per, @PathVariable String codHc) throws ParseException {
        return inventarioService.actaIngreso(per,cod,codHc);
    }
    @GetMapping("/actaIngresoSalida/{per}/{cod}/{codHc}")
    public List<IngresoSalida> actaIngresoSalida(@PathVariable String cod, @PathVariable Integer per, @PathVariable String codHc) throws ParseException {
        return inventarioService.actaIngresoSalida(per,cod,codHc);
    }











}
