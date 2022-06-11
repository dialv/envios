package com.transporte.envios.service;

import com.transporte.envios.model.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente registrar(Cliente p) throws Exception;
    Cliente modificar(Cliente p) throws Exception;
    List<Cliente> listar() throws  Exception;
    Cliente listarPorId(Long id) throws Exception;
    void eliminar(Long id) throws Exception;
}
