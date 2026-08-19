package com.prog.web.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement
public class MenuItemDto {
    private Integer id;
    private String name;
    private String category;
    private Double price;
    private Boolean isVegetarian;
}
