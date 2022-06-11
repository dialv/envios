package com.transporte.envios.controller;

import com.transporte.envios.model.EnvioMaritimo;
import com.transporte.envios.repository.ClienteRepository;
import com.transporte.envios.service.EnvioMaritimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/maritimo")
public class EnvioMaritimoController {

    private final EnvioMaritimoService envioMaritimoService;


    public EnvioMaritimoController(EnvioMaritimoService envioMaritimoService , ClienteRepository clienteRepository) {
        this.envioMaritimoService = envioMaritimoService;
    }



    @GetMapping
    public List<EnvioMaritimo> index() throws Exception {
        return  this.envioMaritimoService.listar();
    }

    @GetMapping("/{id}")
        public EnvioMaritimo finById(@PathVariable Long id) throws Exception {
         EnvioMaritimo EnvioMaritimo= this.envioMaritimoService.listarPorId(id);
         return EnvioMaritimo;
        }

            @PostMapping
            @ResponseStatus(HttpStatus.CREATED)
            public void create(@RequestBody  @Validated EnvioMaritimo EnvioMaritimo) throws Exception {
                this.envioMaritimoService.registrar(EnvioMaritimo);
            }

        @ResponseStatus(HttpStatus.OK)
        @PutMapping("/{id}")
        public ResponseEntity<EnvioMaritimo> update(@PathVariable Long id, @RequestBody  @Validated EnvioMaritimo envioMaritimo) throws Exception {
            EnvioMaritimo envioMaritimoBase= envioMaritimoService.listarPorId(id);

            envioMaritimoBase.setPrecioEnvio(envioMaritimo.getPrecioEnvio());
            envioMaritimoBase.setCantidadProducto(envioMaritimo.getCantidadProducto());
            //se pudo hacer con un mapper y un DTO mas eficiente
            envioMaritimoBase.setPrecioEnvio(envioMaritimo.getPrecioEnvio());
            envioMaritimoBase.setCantidadProducto(envioMaritimo.getCantidadProducto());
            envioMaritimoBase.setCliente(envioMaritimo.getCliente());
            envioMaritimoBase.setFechaRegistro(envioMaritimo.getFechaRegistro());
            envioMaritimoBase.setFechaEntrega(envioMaritimo.getFechaEntrega());
            envioMaritimoBase.setPrecioOriginal(envioMaritimo.getPrecioOriginal());
            envioMaritimoBase.setNumeroGuia(envioMaritimo.getNumeroGuia());
            envioMaritimoBase.setPuertoEntrega(envioMaritimo.getPuertoEntrega());
            envioMaritimoBase.setNumeroFlota(envioMaritimo.getNumeroFlota());

            this.envioMaritimoService.registrar(envioMaritimoBase);
            return new ResponseEntity<EnvioMaritimo>(envioMaritimo, HttpStatus.OK);
        }

        @ResponseStatus(HttpStatus.ACCEPTED)
        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) throws Exception {;
            envioMaritimoService.eliminar(id);
        }


}
