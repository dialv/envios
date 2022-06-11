package com.transporte.envios.controller;

import com.transporte.envios.model.EnvioCamion;
import com.transporte.envios.repository.ClienteRepository;
import com.transporte.envios.service.EnvioCamionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/camion")
public class EnvioCamionController {

    private final EnvioCamionService envioCamionService;
    private final ClienteRepository clienteRepository;


    public EnvioCamionController(EnvioCamionService envioCamionService, ClienteRepository clienteRepository) {
        this.envioCamionService = envioCamionService;
        this.clienteRepository = clienteRepository;
    }



    @GetMapping
    public List<EnvioCamion> index() throws Exception {
        return  this.envioCamionService.listar();
    }

    @GetMapping("/{id}")
        public EnvioCamion finById(@PathVariable Long id) throws Exception {
         EnvioCamion envioCamion= this.envioCamionService.listarPorId(id);
         return envioCamion;
        }

            @PostMapping
            @ResponseStatus(HttpStatus.CREATED)
            public void create(@RequestBody  @Validated EnvioCamion envioCamion) throws Exception {
                this.envioCamionService.registrar(envioCamion);
            }

        @ResponseStatus(HttpStatus.OK)
        @PutMapping("/{id}")
        public ResponseEntity<EnvioCamion> update(@PathVariable Long id, @RequestBody @Validated EnvioCamion envioCamion) throws Exception {
            EnvioCamion envioCamionBase= envioCamionService.listarPorId(id);

            //se pudo hacer con un mapper y un DTO mas eficiente
            envioCamionBase.setPrecioEnvio(envioCamion.getPrecioEnvio());
            envioCamionBase.setCantidadProducto(envioCamion.getCantidadProducto());
            envioCamionBase.setCliente(envioCamion.getCliente());
            envioCamionBase.setBodegaEntrega(envioCamion.getBodegaEntrega());
            envioCamionBase.setFechaRegistro(envioCamion.getFechaRegistro());
            envioCamionBase.setFechaEntrega(envioCamion.getFechaEntrega());
            envioCamionBase.setNumeroPlaca(envioCamion.getNumeroPlaca());
            envioCamionBase.setPrecioOriginal(envioCamion.getPrecioOriginal());
            envioCamionBase.setNumeroGuia(envioCamion.getNumeroGuia());

            this.envioCamionService.registrar(envioCamionBase);
            return new ResponseEntity<EnvioCamion>(envioCamionBase, HttpStatus.OK);
        }

        @ResponseStatus(HttpStatus.ACCEPTED)
        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) throws Exception {
            envioCamionService.eliminar(id);
        }


}
