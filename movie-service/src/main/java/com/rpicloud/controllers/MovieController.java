package com.rpicloud.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rpicloud.IPreferenceService;
import com.rpicloud.models.Movie;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mixmox on 10/06/16.
 */

@RestController
@Component
public class MovieController {
    private ArrayList<Movie> defaultMovies = new ArrayList<Movie>();
    private ArrayList<Movie> actionMovies = new ArrayList<Movie>();
    private ArrayList<Movie> kidsMovies = new ArrayList<Movie>();

    public MovieController() {
        populateMovies();
    }


    @CrossOrigin(origins = "http://localhost:8000")
    @HystrixCommand(fallbackMethod = "moviesFallback", commandKey = "moviesFallback")
    @RequestMapping("/movies/{userId}")
    public ResponseEntity<List<Movie>> movies(@PathVariable int userId){
        System.out.println("movies invoked");

        // Try user preference
        IPreferenceService preferenceService = Feign.builder()
                .decoder(new JacksonDecoder())
                .target(IPreferenceService.class, "http://localhost:8002");
//                .target(IPreferenceService.class, "http://preference-service:8080");

        String preference = preferenceService.preferences(userId);
        if (preference.equals("action")){
            return new ResponseEntity<>(actionMovies, HttpStatus.OK);
        }
        else if (preference.equals("kids")) {
            return new ResponseEntity<>(kidsMovies, HttpStatus.OK);
        }

        return moviesFallback(userId);
    }


    public ResponseEntity<List<Movie>> moviesFallback(@PathVariable int userId){
        System.out.println("moviesFallback invoked");
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

        kidsMovies.add(new Movie("Ratatouille","http://ia.media-imdb.com/images/M/MV5BMTMzODU0NTkxMF5BMl5BanBnXkFtZTcwMjQ4MzMzMw@@._V1_SY1000_CR0,0,674,1000_AL_.jpg", "Marlon Brando, Al Pacino, James Caan, Diane Keaton", 2007, 1));
        kidsMovies.add(new Movie("The Incredibles","http://ia.media-imdb.com/images/M/MV5BMTY5OTU0OTc2NV5BMl5BanBnXkFtZTcwMzU4MDcyMQ@@._V1_SY1000_CR0,0,675,1000_AL_.jpg", "Tim Robbins, Morgan Freeman, Bob Gunton, William Sadler", 2004, 2));
        kidsMovies.add(new Movie("Shrek","http://ia.media-imdb.com/images/M/MV5BMTk2NTE1NTE0M15BMl5BanBnXkFtZTgwNjY4NTYxMTE@._V1_SY1000_CR0,0,675,1000_AL_.jpg", "Liam Neeson, Ralph Fiennes, Ben Kingsley, Caroline Goodall", 1993, 3));
        kidsMovies.add(new Movie("The Lion King","http://ia.media-imdb.com/images/M/MV5BMjEyMzgwNTUzMl5BMl5BanBnXkFtZTcwNTMxMzM3Ng@@._V1_SY1000_CR0,0,772,1000_AL_.jpg", "Robert De Niro, Cathy Moriarty, Joe Pesci, Frank Vincent", 1980, 4));
        kidsMovies.add(new Movie("Finding Nemo","http://ia.media-imdb.com/images/M/MV5BMTY1MTg1NDAxOV5BMl5BanBnXkFtZTcwMjg1MDI5Nw@@._V1_SY1000_CR0,0,675,1000_AL_.jpg", "Humphrey Bogart, Ingrid Bergman, Paul Henreid, Claude Rains", 1942, 5));
        kidsMovies.add(new Movie("Monsters, Inc.","http://ia.media-imdb.com/images/M/MV5BMTY1NTI0ODUyOF5BMl5BanBnXkFtZTgwNTEyNjQ0MDE@._V1_.jpg", "Jack Nicholson, Louise Fletcher, Michael Berryman, Peter Brocco", 1975, 6));
        kidsMovies.add(new Movie("Wall-E","http://ia.media-imdb.com/images/M/MV5BMTczOTA3MzY2N15BMl5BanBnXkFtZTcwOTYwNjE2MQ@@._V1_.jpg", "Clark Gable, Vivien Leigh, Thomas Mitchell, Barbara O'Neil", 1939, 7));
        kidsMovies.add(new Movie("Up","http://ia.media-imdb.com/images/M/MV5BMTk3NDE2NzI4NF5BMl5BanBnXkFtZTgwNzE1MzEyMTE@._V1_SY1000_CR0,0,664,1000_AL_.jpg", "Orson Welles, Joseph Cotten, Dorothy Comingore, Agnes Moorehead", 1941, 8));
        kidsMovies.add(new Movie("The Jungle Book","http://ia.media-imdb.com/images/M/MV5BMTc3NTUzNTI4MV5BMl5BanBnXkFtZTgwNjU0NjU5NzE@._V1_SY1000_SX675_AL_.jpg", "Judy Garland, Frank Morgan, Ray Bolger, Bert Lahr", 1939, 9));
        kidsMovies.add(new Movie("The Angry Birds Movie","http://ia.media-imdb.com/images/M/MV5BMjMwMjgyMDk0MF5BMl5BanBnXkFtZTgwNDIxOTI4NzE@._V1_SY1000_SX675_AL_.jpg", "Peter O'Toole, Alec Guinness, Anthony Quinn, Jack Hawkins", 1962, 10));

        actionMovies.add(new Movie("The Dark Knight","http://ia.media-imdb.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_SY1000_CR0,0,675,1000_AL_.jpg", "Marlon Brando, Al Pacino, James Caan, Diane Keaton", 1972, 1));
        actionMovies.add(new Movie("Heat","http://ia.media-imdb.com/images/M/MV5BMTM1NDc4ODkxNV5BMl5BanBnXkFtZTcwNTI4ODE3MQ@@._V1_.jpg", "Tim Robbins, Morgan Freeman, Bob Gunton, William Sadler", 1994, 2));
        actionMovies.add(new Movie("Inception","http://ia.media-imdb.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SY1000_CR0,0,675,1000_AL_.jpg", "Liam Neeson, Ralph Fiennes, Ben Kingsley, Caroline Goodall", 1993, 3));
        actionMovies.add(new Movie("Kill Bill: Vol 1.","http://ia.media-imdb.com/images/M/MV5BMTU1NDg1Mzg4M15BMl5BanBnXkFtZTYwMDExOTc3._V1_.jpg", "Robert De Niro, Cathy Moriarty, Joe Pesci, Frank Vincent", 1980, 4));
        actionMovies.add(new Movie("Gladiator","http://ia.media-imdb.com/images/M/MV5BMTgwMzQzNTQ1Ml5BMl5BanBnXkFtZTgwMDY2NTYxMTE@._V1_SY1000_CR0,0,675,1000_AL_.jpg", "Humphrey Bogart, Ingrid Bergman, Paul Henreid, Claude Rains", 1942, 5));
        actionMovies.add(new Movie("Saving Private Ryan","http://ia.media-imdb.com/images/M/MV5BNjczODkxNTAxN15BMl5BanBnXkFtZTcwMTcwNjUxMw@@._V1_.jpg", "Jack Nicholson, Louise Fletcher, Michael Berryman, Peter Brocco", 1975, 6));
        actionMovies.add(new Movie("Terminator 2 - Judgement Day","http://ia.media-imdb.com/images/M/MV5BMTI4MDAwMDY3N15BMl5BanBnXkFtZTcwODIwMzMzMQ@@._V1._CR46,1,342,473_.jpg", "Clark Gable, Vivien Leigh, Thomas Mitchell, Barbara O'Neil", 1939, 7));
        actionMovies.add(new Movie("The Bourne Ultimatum","http://ia.media-imdb.com/images/M/MV5BMTgzNjMwOTM3N15BMl5BanBnXkFtZTcwMzA5MDY0MQ@@._V1_.jpg", "Orson Welles, Joseph Cotten, Dorothy Comingore, Agnes Moorehead", 1941, 8));
        actionMovies.add(new Movie("The Dark Knoght Rises","http://ia.media-imdb.com/images/M/MV5BMTk4ODQzNDY3Ml5BMl5BanBnXkFtZTcwODA0NTM4Nw@@._V1_.jpg", "Judy Garland, Frank Morgan, Ray Bolger, Bert Lahr", 1939, 9));
        actionMovies.add(new Movie("The Matrix","http://ia.media-imdb.com/images/M/MV5BMTkxNDYxOTA4M15BMl5BanBnXkFtZTgwNTk0NzQxMTE@._V1_.jpg", "Peter O'Toole, Alec Guinness, Anthony Quinn, Jack Hawkins", 1962, 10));

    }
}
