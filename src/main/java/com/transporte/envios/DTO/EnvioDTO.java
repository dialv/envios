package com.transporte.envios.DTO;

import com.transporte.envios.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvioDTO {

    private String id;
    private String tipoProducto;
    private Integer cantidadProducto;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaEntrega;
    private BigDecimal precioEnvio;
    private String numeroGuia;
    private Cliente cliente;

}
