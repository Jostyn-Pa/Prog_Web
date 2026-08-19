package com.prog.web.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@XmlRootElement
public class Book {
    private Integer id;
    private String title;
    private String author;
    private Double price;
}