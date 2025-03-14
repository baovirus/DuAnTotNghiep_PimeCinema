package com.poly.demo.service;

import com.poly.demo.entity.Movie;
import com.poly.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> getNowShowingMovies() {
        return movieRepository.findNowShowingMovies();
    }

    public List<Movie> getUpcomingMovies() {
        return movieRepository.findUpcomingMovies();
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public List<Movie> searchMoviesByName(String name) {
        return movieRepository.findByNameContaining(name);
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }
    
    public Page<Movie> getMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("movieId").ascending());
        return movieRepository.findAll(pageable);
    }

    public Movie updateMovie(Long id, Movie updatedMovie) {
        return movieRepository.findById(id).map(movie -> {
            movie.setName(updatedMovie.getName());
            movie.setTags(updatedMovie.getTags());
            movie.setDuration(updatedMovie.getDuration());
            movie.setReleaseDate(updatedMovie.getReleaseDate());
            movie.setEndDate(updatedMovie.getEndDate());
            movie.setViewCount(updatedMovie.getViewCount());
            movie.setCountry(updatedMovie.getCountry());
            movie.setProducer(updatedMovie.getProducer());
            movie.setDirector(updatedMovie.getDirector());
            movie.setActors(updatedMovie.getActors());
            movie.setDescription(updatedMovie.getDescription());
            movie.setThumbnail(updatedMovie.getThumbnail());
            movie.setTrailer(updatedMovie.getTrailer());
            return movieRepository.save(movie);
        }).orElse(null);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
