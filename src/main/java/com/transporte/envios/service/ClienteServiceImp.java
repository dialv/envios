package com.transporte.envios.service;

import com.transporte.envios.model.Cliente;
import com.transporte.envios.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente registrar(Cliente p) throws Exception {
        return clienteRepository.save(p);
    }

    @Override
    public Cliente modificar(Cliente p) throws Exception {
        return clienteRepository.save(p);
    }

    @Override
    public List<Cliente> listar() throws Exception {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente listarPorId(Long id) throws Exception {
        return clienteRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        clienteRepository.deleteById(id);
    }
}
