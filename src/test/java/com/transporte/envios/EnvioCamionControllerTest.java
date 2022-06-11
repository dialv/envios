package com.transporte.envios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transporte.envios.model.EnvioCamion;
import com.transporte.envios.service.EnvioCamionService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EnvioCamionControllerTest {


    @Autowired
    private MockMvc mockMvc;

   private static final String token= "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtZWx2aW4ycm1AZ21haWwuY29tIiwiZXhwIjoxNjUwMjk3NjM3LCJyb2xlIjoiQURNSU4iLCJuYW1lIjoibWVsdmluIHJlbmUiLCJ1c2VySWQiOjEsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iXX0.fI8mQsgw0b8oyy8GHfbLQKOytnKMy9wVKYw1cphzFCMir1DY70P0QARKSZ3a0bFaS689h_pcCYME-MR-lUgDzg";

    @MockBean
    private EnvioCamionService envioCamionService;

    @BeforeEach
    void setup() throws Exception {

        given(this.envioCamionService.listar())
                .willReturn(Lists.newArrayList(envioCamionDTO()));

        given(this.envioCamionService.modificar(envioCamionDTO()))
                .willReturn(envioCamionDTOUpdate());

        given(this.envioCamionService.listarPorId(any())).willReturn(envioCamionDTO());

      //  EnvioCamion envioCamionBase= envioCamionService.listarPorId(id);
        doNothing().when(this.envioCamionService).eliminar(any());

    }

    private EnvioCamion envioCamionDTO() {
        EnvioCamion envio = new EnvioCamion();
        envio.setBodegaEntrega("san miguel");
        envio.setNumeroPlaca("12");
        envio.setCantidadProducto(12);
        envio.setFechaEntrega(new Date());
        envio.setFechaRegistro(new Date());
        envio.setNumeroGuia("122");
        return envio;
    };

    private EnvioCamion envioCamionDTOUpdate() {
        EnvioCamion envio = new EnvioCamion();
        envio.setId(2L);
        envio.setBodegaEntrega("san miguel");
        envio.setNumeroPlaca("12");
        envio.setPrecioEnvio(new BigDecimal("12"));
        envio.setCantidadProducto(12);
        envio.setFechaEntrega(new Date());
        envio.setFechaRegistro(new Date());
        envio.setNumeroGuia("122");
        return envio;
    };


    @Test
    void getEnvio() throws Exception {
        String token= "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtZWx2aW4ycm1AZ21haWwuY29tIiwiZXhwIjoxNjUwMjk3NjM3LCJyb2xlIjoiQURNSU4iLCJuYW1lIjoibWVsdmluIHJlbmUiLCJ1c2VySWQiOjEsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iXX0.fI8mQsgw0b8oyy8GHfbLQKOytnKMy9wVKYw1cphzFCMir1DY70P0QARKSZ3a0bFaS689h_pcCYME-MR-lUgDzg";
        mockMvc.perform(get("/api/camion")
                .header("Authorization",token))
                .andExpect(status().isOk());
    }

    @Test
    void processEnvio() throws Exception {

        mockMvc.perform(
                post("/api/camion")
                        .header("Authorization",token)
                .content(asJsonString(envioCamionDTO()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updateEnvioPI() throws Exception
    {

        mockMvc.perform( MockMvcRequestBuilders
                .put("/api/camion/{id}", 1L)
                .content(asJsonString(envioCamionDTOUpdate()))
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cantidadProducto").value("12"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.precioEnvio").value("12"));
    }

    @Test
    public void deleteEnvio() throws Exception
    {

        mockMvc.perform( MockMvcRequestBuilders
                .delete("/api/camion/{id}", 1L)
                .header("Authorization",token))
                .andExpect(status().isAccepted());
    }
}
