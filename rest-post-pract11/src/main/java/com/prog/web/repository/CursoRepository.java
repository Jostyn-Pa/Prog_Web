package com.prog.web.repository;

import com.prog.web.db.Curso;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends FullEntityRepository<Curso, Integer> {
    @Query("""
            SELECT p
            from Curso p
            WHERE (:categoria IS NULL OR p.categoria = :categoria )
            AND (:precioMaximo IS NULL OR p.precio <= :precioMaximo)
            """)
    List<Curso> searchQuery(@QueryParam("categoria")String categoria, @QueryParam("precioMaximo")Double precioMaximo);
}
