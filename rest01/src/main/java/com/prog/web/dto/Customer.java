package com.prog.web.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Builder
public class Customer {
    private Integer id;
    private String name;
    private String direccion;
}
