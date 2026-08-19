package com.prog.web.main;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoGameDto {
    private Integer id;
    private String title;
    private String platform;
    private Double price;
    private Boolean isMultiplayer;
    private Integer releaseYear;
}
