package com.prog.web.repository;

import com.prog.web.db.Libro;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends FullEntityRepository<Libro, Integer> {
    /*
    Reto: Escribe un método con @Query que filtre los libros por género (de la tabla libro)
                        y por nacionalidad (de la tabla autor).
     */

    @Query("""
            SELECT l
            FROM Libro l
            WHERE (:genero IS NULL OR l.genero = :genero)
            AND (:nacionalidad IS NULL OR l.author.nacionalidad = :nacionalidad)
            """)
    List<Libro> filter(@QueryParam("genero") String genero, @QueryParam("nacionalidad") String nacionalidad);
}
