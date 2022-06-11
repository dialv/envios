package com.transporte.envios.DTO;

import com.transporte.envios.model.Envio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvioMaritimoDTO extends EnvioDTO {

    private String puertoEntrega;
    private String numeroFlota;
}
