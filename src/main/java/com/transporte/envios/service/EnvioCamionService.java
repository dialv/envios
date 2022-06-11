package com.transporte.envios.service;

import com.transporte.envios.model.EnvioCamion;

import java.util.List;

public interface EnvioCamionService {

    EnvioCamion registrar(EnvioCamion p) throws Exception;
    EnvioCamion modificar(EnvioCamion p) throws Exception;
    List<EnvioCamion> listar() throws  Exception;
    EnvioCamion listarPorId(Long id) throws Exception;
    void eliminar(Long id) throws Exception;
}
