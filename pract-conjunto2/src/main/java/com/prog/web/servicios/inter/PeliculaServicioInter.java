package com.prog.web.servicios.inter;

import com.prog.web.db.Pelicula;

import java.util.List;
import java.util.Optional;

public interface PeliculaServicioInter {
    List<Pelicula> findAll();
    Optional<Pelicula> findById(Integer id);
    Pelicula save(Pelicula pelicula);
    Optional<Pelicula> update(Integer id, Pelicula pelicula);
    boolean delete(Integer id);
    List<Pelicula> filtrosOpcionales(
            String titulo,
            String genero,
            Double minPrecio,
            Double maxPrecio,
            Integer anio
    );
    List<Pelicula> filtrosObligatorios(
            Double minPrecio,
            Double maxPrecio
    );
    List<Pelicula> findDirectorById(Integer id);
}
