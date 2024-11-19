package com.example.parcial_web_back.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DTOContrato {
    private Long id;
    private String identificador;
    private Double valor;
    private String nombreContratante;
    private String documentoContratante;
    private String nombreContratantista;
    private String documentoContratantista;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;

}
