package com.prog.web.repository;

import com.prog.web.db.Producto;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends FullEntityRepository<Producto, Integer> {

    @Query("""
        SELECT p
        FROM Producto p
        WHERE (:nombre IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')))
        AND (:minPrecio IS NULL OR p.precio >= :minPrecio)
        AND (:maxPrecio IS NULL OR p.precio <= :maxPrecio)
        """)
    List<Producto> query(
            @QueryParam("nombre") String nombre,
            @QueryParam("minPrecio") Double minPrecio,
            @QueryParam("maxPrecio") Double maxPrecio
    );
}