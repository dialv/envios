package com.transporte.envios.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Envio  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Tipop de producto no puede ser null")
    private String tipoProducto;
    @PositiveOrZero
    @NotNull(message = "cantidad de producto no puede ser negativo")
    private Integer cantidadProducto;
    private Date fechaRegistro;
    private Date fechaEntrega;
    @PositiveOrZero
    @NotNull(message = "Precio debe ser positivo o cero")
    private BigDecimal precioEnvio;
    @Size(min = 10,max = 10,message = "la longitud del numero de guia debe ser de 10")
    private String numeroGuia;


    @NotNull(message = "Precio Original debe ser positivo o 0")
    @PositiveOrZero(message = "el precio de envio original debe ser positivo o 0")
    private BigDecimal precioOriginal;

    @NotNull(message = "Debe de seleccionar un cliente")
    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

}
