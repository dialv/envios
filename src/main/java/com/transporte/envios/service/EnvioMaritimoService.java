package com.transporte.envios.service;

import com.transporte.envios.model.EnvioMaritimo;

import java.util.List;

public interface EnvioMaritimoService {

    EnvioMaritimo registrar(EnvioMaritimo p) throws Exception;
    EnvioMaritimo modificar(EnvioMaritimo p) throws Exception;
    List<EnvioMaritimo> listar() throws  Exception;
    EnvioMaritimo listarPorId(Long id) throws Exception;
    void eliminar(Long id) throws Exception;
}
