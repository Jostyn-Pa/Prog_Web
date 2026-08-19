package com.prog.web.repository;

import com.prog.web.db.Movie;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends FullEntityRepository<Movie, Integer> {
    List<Movie> findByGenre(String genre);
    List<Movie> findByTitleLike(String title);
    List<Movie> findByRatingGreaterThanEquals(Double rating);
}
