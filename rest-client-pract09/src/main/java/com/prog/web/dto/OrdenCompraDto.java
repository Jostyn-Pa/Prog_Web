package com.prog.web.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement
public class OrdenCompraDto {
    private Integer id;
    private Integer cantidad;
    private ClienteDto clienteId;
    private ProductoDto productoId;
}
