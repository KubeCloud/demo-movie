package com.rpicloud.controllers;

import com.rpicloud.models.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mixmox on 10/06/16.
 */

@RestController
public class MovieController {
    private ArrayList<Movie> defaultMovies = new ArrayList<Movie>();
    private ArrayList<Movie> actionMovies = new ArrayList<Movie>();
    private ArrayList<Movie> kidsMovies = new ArrayList<Movie>();

    public MovieController() {
        populateMovies();
    }

    @RequestMapping("/movies")
    public ResponseEntity<List<Movie>> movies() {
        return new ResponseEntity<>(defaultMovies, HttpStatus.OK);
    }


    private void populateMovies() {
        // Default movies
        defaultMovies.add(new Movie("The Godfather","http://ia.media-imdb.com/images/M/MV5BMjEyMjcyNDI4MF5BMl5BanBnXkFtZTcwMDA5Mzg3OA@@._V1._SX140_CR0,0,140,209_.jpg", "Marlon Brando, Al Pacino, James Caan, Diane Keaton", 1972));
        defaultMovies.add(new Movie("The Shawshank Redemption","http://ia.media-imdb.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg", "Tim Robbins, Morgan Freeman, Bob Gunton, William Sadler", 1994));
//        defaultMovies.add(new Movie("The Shawshank Redemption","", "", ));
//        defaultMovies.add(new Movie("The Shawshank Redemption","", "", ));
//        defaultMovies.add(new Movie("The Shawshank Redemption","", "", ));
//        defaultMovies.add(new Movie("The Shawshank Redemption","", "", ));
//        defaultMovies.add(new Movie("The Shawshank Redemption","", "", ));
//        defaultMovies.add(new Movie("The Shawshank Redemption","", "", ));
//        defaultMovies.add(new Movie("The Shawshank Redemption","", "", ));
//        defaultMovies.add(new Movie("The Shawshank Redemption","", "", ));

    }
}
