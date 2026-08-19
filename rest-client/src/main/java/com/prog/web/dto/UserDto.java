package com.prog.web.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
//Este no es obligatorio que tenga el mismo nombre, pero que si las variables de instancias sean las mismas
public class UserDto {


    private Integer id;

    private String name;
    private String username;
    private String email;

    private String addressStreet;

    private String addressSuite;

    private String addressCity;

    private String addressZipcode;

    private BigDecimal addressGeoLat;

    private BigDecimal addressGeoLng;

    private String phone;

    private String website;

    private String companyName;

    private String companyCatchPhrase;

    private String companyBs;
}
