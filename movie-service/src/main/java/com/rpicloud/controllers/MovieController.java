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
        defaultMovies.add(new Movie("The Godfather","http://ia.media-imdb.com/images/M/MV5BMjEyMjcyNDI4MF5BMl5BanBnXkFtZTcwMDA5Mzg3OA@@._V1._SX140_CR0,0,140,209_.jpg", "Marlon Brando, Al Pacino, James Caan, Diane Keaton", 1972, 1));
        defaultMovies.add(new Movie("The Shawshank Redemption","http://ia.media-imdb.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1._SY209_CR0,0,140,209_.jpg", "Tim Robbins, Morgan Freeman, Bob Gunton, William Sadler", 1994, 2));
        defaultMovies.add(new Movie("Schindler's List","http://ia.media-imdb.com/images/M/MV5BMzMwMTM4MDU2N15BMl5BanBnXkFtZTgwMzQ0MjMxMDE@._V1._SX140_CR0,0,140,209_.jpg", "Liam Neeson, Ralph Fiennes, Ben Kingsley, Caroline Goodall", 1993, 3));
        defaultMovies.add(new Movie("Raging Bull ","http://ia.media-imdb.com/images/M/MV5BMjIxOTg3OTc5MF5BMl5BanBnXkFtZTcwNzkwNjMwNA@@._V1._SX140_CR0,0,140,209_.jpg", "Robert De Niro, Cathy Moriarty, Joe Pesci, Frank Vincent", 1980, 4));
        defaultMovies.add(new Movie("Casablanca","http://ia.media-imdb.com/images/M/MV5BMjQwNDYyNTk2N15BMl5BanBnXkFtZTgwMjQ0OTMyMjE@._V1._SX140_CR0,0,140,209_.jpg", "Humphrey Bogart, Ingrid Bergman, Paul Henreid, Claude Rains", 1942, 5));
        defaultMovies.add(new Movie("One Flew Over the Cuckoo's Nest","http://ia.media-imdb.com/images/M/MV5BMTk5OTA4NTc0NF5BMl5BanBnXkFtZTcwNzI5Mzg3OA@@._V1._SY209_CR8,0,140,209_.jpg", "Jack Nicholson, Louise Fletcher, Michael Berryman, Peter Brocco", 1975, 6));
        defaultMovies.add(new Movie("Gone with the Wind","http://ia.media-imdb.com/images/M/MV5BNDUwMjAxNTU1MF5BMl5BanBnXkFtZTgwMzg4NzMxMDE@._V1._SX140_CR0,0,140,209_.jpg", "Clark Gable, Vivien Leigh, Thomas Mitchell, Barbara O'Neil", 1939, 7));
        defaultMovies.add(new Movie("Citizen Kane","http://ia.media-imdb.com/images/M/MV5BMTQ2Mjc1MDQwMl5BMl5BanBnXkFtZTcwNzUyOTUyMg@@._V1._SY209_CR0,0,140,209_.jpg", "Orson Welles, Joseph Cotten, Dorothy Comingore, Agnes Moorehead", 1941, 8));
        defaultMovies.add(new Movie("The Wizard of Oz ","http://ia.media-imdb.com/images/M/MV5BMTU0MTA2OTIwNF5BMl5BanBnXkFtZTcwMzA0Njk3OA@@._V1._SY209_CR7,0,140,209_.jpg", "Judy Garland, Frank Morgan, Ray Bolger, Bert Lahr", 1939, 9));
        defaultMovies.add(new Movie("Lawrence of Arabia","http://ia.media-imdb.com/images/M/MV5BMzAwMjM4NzA2OV5BMl5BanBnXkFtZTcwMDI0NzAwMQ@@._V1._SY209_CR1,0,140,209_.jpg", "Peter O'Toole, Alec Guinness, Anthony Quinn, Jack Hawkins", 1962, 10));

    }
}
