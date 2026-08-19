package com.prog.web.servicios.inter;

import com.prog.web.db.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> findAll();
    Optional<Movie> findById(Integer id);
    Movie save(Movie movie);
    Optional<Movie> update(Integer id, Movie movie);
    Boolean delete(Integer id);
    List<Movie> findByGenre(String genre);
    List<Movie> findByTitleLike(String title);
    List<Movie> findByRatingGreaterThanEquals(Double rating);
}
