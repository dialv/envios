package com.transporte.envios.repository;


import com.transporte.envios.model.Cliente;
import com.transporte.envios.model.EnvioCamion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}