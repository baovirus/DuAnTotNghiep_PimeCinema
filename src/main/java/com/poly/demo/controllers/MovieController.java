package com.poly.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.demo.entity.Movie;
import com.poly.demo.entity.Showtime;
import com.poly.demo.service.MovieService;
import com.poly.demo.service.ShowtimeService;


@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    
    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping("/")
    public String listMovies(Model model) {
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			UserDetails user = (UserDetails) principal;
			model.addAttribute("user", user); // Gửi user đến Thymeleaf
		} else {
			model.addAttribute("user", null); // Nếu chưa đăng nhập, user sẽ là null
		}
        return "movies-list"; // Tên file Thymeleaf (movies.html)
    }
    
    @GetMapping("/now_showing")
    public String listMoviesNowShowing(Model model) {
        List<Movie> movies = movieService.getNowShowingMovies();
        model.addAttribute("movies", movies);
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			UserDetails user = (UserDetails) principal;
			model.addAttribute("user", user); // Gửi user đến Thymeleaf
		} else {
			model.addAttribute("user", null); // Nếu chưa đăng nhập, user sẽ là null
		}
        return "movies-list"; // Tên file Thymeleaf (movies.html)
    }
    
    @GetMapping("/upcomming")
    public String listMoviesUpcomming(Model model) {
        List<Movie> movies = movieService.getUpcommingMovies();
        model.addAttribute("movies", movies);
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			UserDetails user = (UserDetails) principal;
			model.addAttribute("user", user); // Gửi user đến Thymeleaf
		} else {
			model.addAttribute("user", null); // Nếu chưa đăng nhập, user sẽ là null
		}
        return "movies-list"; // Tên file Thymeleaf (movies.html)
    }
    
    /*
     * 
     * */
    @GetMapping("/movie-detail/{id}")
    public String MovieDetail(@PathVariable Long id, Model model) {
    	Optional<Movie> movie = movieService.getMovieById(id);
    	model.addAttribute("movie", movie.get());
    	
    	List<Showtime> showtime = showtimeService.getShowtimesByMovieId(id);
    	model.addAttribute("showtime", showtime);
    	
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			UserDetails user = (UserDetails) principal;
			model.addAttribute("user", user); // Gửi user đến Thymeleaf
		} else {
			model.addAttribute("user", null); // Nếu chưa đăng nhập, user sẽ là null
		}
    	return "movie-detail";
    }
}
