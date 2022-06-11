package com.transporte.envios.controller;

import com.transporte.envios.model.Cliente;
import com.transporte.envios.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cliente")
public class ClienteController {


    private final ClienteService clienteService;


    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }



    @GetMapping
    public List<Cliente> index() throws Exception {
        return  this.clienteService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody  @Validated Cliente cliente) throws Exception {
        this.clienteService.registrar(cliente);
    }

}
