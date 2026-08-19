package com.prog.web.servicios.impl;

import com.prog.web.db.Movie;
import com.prog.web.repository.MovieRepository;
import com.prog.web.servicios.inter.MovieService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Inject
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Integer id) {
        return movieRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    @Transactional
    public Optional<Movie> update(Integer id, Movie movie) {
        return movieRepository.findOptionalBy(id).map(existingMovie -> {
            movie.setId(existingMovie.getId());
            return movieRepository.save(movie);
        });
    }

    @Override
    @Transactional
    public Boolean delete(Integer id) {
        return movieRepository.findOptionalBy(id).map(ex -> {
            movieRepository.remove(ex);
            return true;
        }).orElse(false);
    }

    @Override
    public List<Movie> findByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    @Override
    public List<Movie> findByTitleLike(String title) {
        if(title == null || title.isEmpty())  {
            return movieRepository.findAll();
        } else {
            return movieRepository.findByTitleLike("%" + title + "%");
        }
    }

    @Override
    public List<Movie> findByRatingGreaterThanEquals(Double rating) {
        return movieRepository.findByRatingGreaterThanEquals(rating);
    }
}
