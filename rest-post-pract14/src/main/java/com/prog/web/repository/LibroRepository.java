package com.prog.web.repository;

import com.prog.web.db.Libro;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends FullEntityRepository<Libro, Integer> {
    //Implementar búsqueda de libros usando QueryParams
    //GET /api/libros?titulo=&minPrecio=&maxPrecio=&categoria=
    @Query("""
            SELECT l
            FROM Libro l
            WHERE (:titulo IS NULL OR LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')))
            AND (:minPrecio IS NULL OR l.precio >= :minPrecio)
            AND (:maxPrecio IS NULL OR l.precio <= :maxPrecio)
            AND (:categoria IS NULL OR LOWER(l.categoria) LIKE LOWER(CONCAT('%', :categoria, '%')))
            """)
    List<Libro> findByTituloAndMinPrecioAndMaxPrecioAndCategoria(
            @QueryParam("titulo") String titulo,
            @QueryParam("minPrecio") Double minPrecio,
            @QueryParam("maxPrecio") Double maxPrecio,
            @QueryParam("categoria") String categoria
    );

    List<Libro> findByAutorId(Integer id);


    @Query("""
            SELECT l
            FROM Libro l
            WHERE (:categoria IS NULL OR l.categoria = :categoria)
            """)
    List<Libro> findByCategoria(@QueryParam("categoria") String categoria);

    @Query("""
            SELECT l
            FROM Libro l
            WHERE (l.precio BETWEEN :minPrecio AND :maxPrecio)
            """)
    List<Libro> findByPrecioBetween(@QueryParam("minPrecio") Double minPrecio,@QueryParam("maxPrecio") Double maxPrecio);

    @Query("""
            SELECT l
            FROM Libro l
            WHERE (:titulo IS NULL OR LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')))
            """)
    List<Libro> findByTituloLikeIgnoreCase(@QueryParam("titulo") String titulo);
}
