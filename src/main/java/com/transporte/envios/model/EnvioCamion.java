package com.transporte.envios.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EnvioCamion extends Envio{
    @NotNull
    private String bodegaEntrega;
    @NotNull
    private String numeroPlaca;
}
