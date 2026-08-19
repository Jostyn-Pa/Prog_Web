package com.prog.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibroDto {
    private Integer id;
    private String titulo;
    private String genero;
    private AuthorDto authorDto;
}
