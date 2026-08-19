package com.prog.web.repository;

import com.prog.web.db.Pelicula;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends FullEntityRepository<Pelicula, Integer> {
    /*
    titulo
genero
minPrecio
maxPrecio
anio
     */

    @Query("""
            SELECT p
            FROM Pelicula p
            WHERE (:titulo IS NULL OR LOWER(p.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')))
            AND (:genero IS NULL OR LOWER(p.genero) LIKE LOWER(CONCAT('%', :genero, '%')))
            AND (:minPrecio IS NULL OR p:precio >= :minPrecio)
            AND (:maxPrecio IS NULL OR p:precio <= :maxPrecio)
            AND (:anio IS NULL OR p:anio = :anio)
            """)
    List<Pelicula> filtrosOpcionales(
            @QueryParam("titulo")String titulo,
            @QueryParam("genero")String genero,
            @QueryParam("minPrecio")Double minPrecio,
            @QueryParam("maxPrecio")Double maxPrecio,
            @QueryParam("anio")Integer anio
    );

    @Query("""
            SELECT p
            FROM Pelicula p
            WHERE (p.precio BETWEEN :minPrecio AND :maxPrecio )
            """)
    List<Pelicula> filtrosObligatorios(
            @QueryParam("minPrecio") Double minPrecio,
            @QueryParam("maxPrecio") Double maxPrecio
    );

    List<Pelicula> findByDirectorId(Integer id);

    //Buscar películas cuyo título contenga un texto
    //y cuyo director sea de un país dado.
    @Query("""
    SELECT p
    FROM Pelicula p
    WHERE (:titulo IS NULL OR LOWER(p.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')))
    AND (:pais IS NULL OR LOWER(p.director.pais) = LOWER(:pais))
    """)
    List<Pelicula> buscarPorTituloYPais(
            @QueryParam("titulo") String titulo,
            @QueryParam("pais") String pais
    );
}
