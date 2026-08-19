package com.prog.web.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private Integer edad;
    @Column(nullable = false)
    private String enfermedad;
    @Column(nullable = false)
    private Double costo;
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
}
