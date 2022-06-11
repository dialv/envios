package com.transporte.envios.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EnvioMaritimo extends Envio{
    @NotNull
    private String puertoEntrega;
    @Pattern(regexp = "[A-Za-z]{3}[0-9]{3}[A-Za-z]{1}",message = "El numero de flota debe tener el formato ###$$$#")
    private String numeroFlota;
}
