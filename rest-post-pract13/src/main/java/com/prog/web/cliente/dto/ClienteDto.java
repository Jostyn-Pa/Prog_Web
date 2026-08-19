package com.prog.web.cliente.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDto {
    private Integer id;
    private String nombre;
    private String apellido;
    private String direccion;
}
