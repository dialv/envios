package com.transporte.envios.repository;


import com.transporte.envios.model.EnvioCamion;
import com.transporte.envios.model.EnvioMaritimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvioMaritimoRepository extends JpaRepository<EnvioMaritimo,Long> {
}