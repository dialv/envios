package com.transporte.envios.service;

import com.transporte.envios.model.EnvioMaritimo;
import com.transporte.envios.repository.EnvioMaritimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EnvioMaritimoServiceImp implements EnvioMaritimoService {

    @Autowired
    private EnvioMaritimoRepository envioMaritimoRepository;

    @Override
    public EnvioMaritimo registrar(EnvioMaritimo p) throws Exception {
        return envioMaritimoRepository.save(p);
    }

    @Override
    public EnvioMaritimo modificar(EnvioMaritimo p) throws Exception {
        return envioMaritimoRepository.save(p);
    }

    @Override
    public List<EnvioMaritimo> listar() throws Exception {
        return envioMaritimoRepository.findAll();
    }

    @Override
    public EnvioMaritimo listarPorId(Long id) throws Exception {
        return envioMaritimoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void eliminar(Long id) throws Exception {
         envioMaritimoRepository.deleteById(id);
    }
}
