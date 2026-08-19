package com.prog.web.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Vital para el POST
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(name = "address_street")
    private String addressStreet;

    @Column(name = "address_suite", length = 100)
    private String addressSuite;

    @Column(name = "address_city", length = 100)
    private String addressCity;

    @Column(name = "address_zipcode", length = 20)
    private String addressZipcode;

    @Column(name = "address_geo_lat", precision = 10, scale = 7)
    private BigDecimal addressGeoLat;

    @Column(name = "address_geo_lng", precision = 10, scale = 7)
    private BigDecimal addressGeoLng;

    @Column(length = 50)
    private String phone;

    private String website;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_catch_phrase", columnDefinition = "TEXT")
    private String companyCatchPhrase;

    @Column(name = "company_bs")
    private String companyBs;
}