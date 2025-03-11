package com.poly.demo.service;

import com.poly.demo.entity.Branch;
import com.poly.demo.entity.Movie;
import com.poly.demo.entity.Showtime;
import com.poly.demo.repository.MovieRepository;
import com.poly.demo.repository.ShowtimeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowtimeService {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    public List<Showtime> getAllShowtime() {
        return showtimeRepository.findAll();
    }
    public Showtime getShowtimeById(Long id) {
        return showtimeRepository.findById(id).orElse(null);
    }
    
    public List<Showtime> getShowtimesByMovieAndBranch(Optional<Movie> movie, Optional<Branch> branch) {
        return showtimeRepository.findByMovieAndBranch(movie, branch);
    }

    public List<Showtime> getShowtimesByMovieId(Long movieId) {
        return showtimeRepository.findShowtimesByMovieId(movieId);
    }

    public Showtime addShowtime(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }

    /*
     * public Movie updateMovie(Long id, Movie updatedMovie) {
        return showtimeRepository.findById(id)
                .map(movie -> {
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
                })
                .orElse(null);
    }
     * 
     */
    

    public void deleteShowtime(Long id) {
    	showtimeRepository.deleteById(id);
    }
}
