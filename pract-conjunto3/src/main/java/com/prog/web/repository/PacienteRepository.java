package com.prog.web.repository;

import com.prog.web.db.Paciente;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends FullEntityRepository<Paciente, Integer> {
    List<Paciente> findByMedicoId(Integer id);

    @Query("""
    SELECT p
    FROM Paciente p
    WHERE (:nombre IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')))
    AND (:enfermedad IS NULL OR LOWER(p.enfermedad) LIKE LOWER(CONCAT('%', :enfermedad, '%')))
    AND (:edadMinima IS NULL OR p.edad >= :edadMinima)
    AND (:edadMaxima IS NULL OR p.edad <= :edadMaxima)
    """)
    List<Paciente> filtrosOpcionales(
            @QueryParam("nombre") String nombre,
            @QueryParam("enfermedad") String enfermedad,
            @QueryParam("edadMinima") Integer edadMinima,
            @QueryParam("edadMaxima") Integer edadMaxima
    );

    @Query("""
            SELECT p 
            FROM Paciente p
            WHERE p.costo BETWEEN :minCosto AND :maxCosto
            """)
    List<Paciente> filtroObligatorios(
            @QueryParam("minCosto")Double minCosto,
            @QueryParam("maxCosto")Double maxCosto
    );
}
