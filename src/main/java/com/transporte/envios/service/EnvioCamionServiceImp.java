package com.transporte.envios.service;

import com.transporte.envios.model.EnvioCamion;
import com.transporte.envios.repository.EnvioCamionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EnvioCamionServiceImp implements EnvioCamionService {

    @Autowired
    private  EnvioCamionRepository envioCamionRepository;

    @Override
    public EnvioCamion registrar(EnvioCamion p) throws Exception {
        return envioCamionRepository.save(p);
    }

    @Override
    public EnvioCamion modificar(EnvioCamion p) throws Exception {
        return envioCamionRepository.save(p);
    }

    @Override
    public List<EnvioCamion> listar() throws Exception {
        return envioCamionRepository.findAll();
    }

    @Override
    public EnvioCamion listarPorId(Long id) throws Exception {
        return envioCamionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void eliminar(Long id) throws Exception {
         envioCamionRepository.deleteById(id);
    }
}
