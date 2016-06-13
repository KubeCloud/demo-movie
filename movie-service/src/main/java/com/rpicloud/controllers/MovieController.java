package com.rpicloud.controllers;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rpicloud.IPreferenceService;
import com.rpicloud.models.Movie;
import com.rpicloud.models.Preference;
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
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.moviesFallback.circuitBreaker.requestVolumeThreshold", 3);
        ConfigurationManager.getConfigInstance().setProperty("hystrix.command.moviesFallback.metrics.rollingStats.timeInMilliseconds", 5000);
    }


    @CrossOrigin(origins = "http://192.168.1.51")
    @HystrixCommand(fallbackMethod = "moviesFallback", commandKey = "moviesFallback")
    @RequestMapping("/movies/{userId}")
    public ResponseEntity<List<Movie>> movies(@PathVariable int userId){
        System.out.println("movies invoked");

        // Try user preference
        IPreferenceService preferenceService = Feign.builder()
                .decoder(new JacksonDecoder())
//                .target(IPreferenceService.class, "http://localhost:8002");
                .target(IPreferenceService.class, "http://preference-service:8080");
        Preference preference = preferenceService.preferences(userId);

        if (preference.getName().equals("action")){
            return new ResponseEntity<>(actionMovies, HttpStatus.OK);
        }
        else if (preference.getName().equals("kids")) {
            return new ResponseEntity<>(kidsMovies, HttpStatus.OK);
        }

        return moviesFallback(userId);
    }


    public ResponseEntity<List<Movie>> moviesFallback(@PathVariable int userId){
        System.out.println("moviesFallback invoked");
        return new ResponseEntity<>(defaultMovies, HttpStatus.OK);
    }




    private void populateMovies() {
//        String host = "http://192.168.1.51:8001/";
        String host = "http://192.168.1.51:8001/images/";
        // Default movies
        defaultMovies.add(new Movie("The Godfather", host + "default1.jpg", "Marlon Brando, Al Pacino, James Caan, Diane Keaton", 1972, 1));
        defaultMovies.add(new Movie("The Shawshank Redemption", host + "default2.jpg", "Tim Robbins, Morgan Freeman, Bob Gunton, William Sadler", 1994, 2));
        defaultMovies.add(new Movie("Schindler's List", host + "default3.jpg", "Liam Neeson, Ralph Fiennes, Ben Kingsley, Caroline Goodall", 1993, 3));
        defaultMovies.add(new Movie("Raging Bull ", host + "default4.jpg", "Robert De Niro, Cathy Moriarty, Joe Pesci, Frank Vincent", 1980, 4));
        defaultMovies.add(new Movie("Casablanca", host + "default5.jpg", "Humphrey Bogart, Ingrid Bergman, Paul Henreid, Claude Rains", 1942, 5));
        defaultMovies.add(new Movie("One Flew Over the Cuckoo's Nest", host + "default6.jpg", "Jack Nicholson, Louise Fletcher, Michael Berryman, Peter Brocco", 1975, 6));
        defaultMovies.add(new Movie("Gone with the Wind", host + "default7.jpg", "Clark Gable, Vivien Leigh, Thomas Mitchell, Barbara O'Neil", 1939, 7));
        defaultMovies.add(new Movie("Citizen Kane", host + "default8.jpg", "Orson Welles, Joseph Cotten, Dorothy Comingore, Agnes Moorehead", 1941, 8));
        defaultMovies.add(new Movie("The Wizard of Oz ", host + "default9.jpg", "Judy Garland, Frank Morgan, Ray Bolger, Bert Lahr", 1939, 9));
        defaultMovies.add(new Movie("Lawrence of Arabia", host + "default1.jpg", "Peter O'Toole, Alec Guinness, Anthony Quinn, Jack Hawkins", 1962, 10));

//        kidsMovies.add(new Movie("Ratatouille","http://ia.media-imdb.com/images/M/MV5BMTMzODU0NTkxMF5BMl5BanBnXkFtZTcwMjQ4MzMzMw@@._V1_SY1000_CR0,0,674,1000_AL_.jpg", "Marlon Brando, Al Pacino, James Caan, Diane Keaton", 2007, 1));
        kidsMovies.add(new Movie("The Incredibles",host + "kids1.jpg", "Tim Robbins, Morgan Freeman, Bob Gunton, William Sadler", 2004, 2));
        kidsMovies.add(new Movie("Shrek", host + "kids2.jpg", "Liam Neeson, Ralph Fiennes, Ben Kingsley, Caroline Goodall", 1993, 3));
        kidsMovies.add(new Movie("The Lion King", host + "kids3.jpg", "Robert De Niro, Cathy Moriarty, Joe Pesci, Frank Vincent", 1980, 4));
        kidsMovies.add(new Movie("Finding Nemo", host + "kids4.jpg", "Humphrey Bogart, Ingrid Bergman, Paul Henreid, Claude Rains", 1942, 5));
        kidsMovies.add(new Movie("Monsters, Inc.", host + "kids5.jpg", "Jack Nicholson, Louise Fletcher, Michael Berryman, Peter Brocco", 1975, 6));
        kidsMovies.add(new Movie("Wall-E", host + "kids6.jpg", "Clark Gable, Vivien Leigh, Thomas Mitchell, Barbara O'Neil", 1939, 7));
        kidsMovies.add(new Movie("Up", host + "kids7.jpg", "Orson Welles, Joseph Cotten, Dorothy Comingore, Agnes Moorehead", 1941, 8));
        kidsMovies.add(new Movie("The Jungle Book", host + "kids8.jpg", "Judy Garland, Frank Morgan, Ray Bolger, Bert Lahr", 1939, 9));
        kidsMovies.add(new Movie("The Angry Birds Movie", host + "kids9.jpg", "Peter O'Toole, Alec Guinness, Anthony Quinn, Jack Hawkins", 1962, 10));

        actionMovies.add(new Movie("The Dark Knight", host + "action1.jpg", "Marlon Brando, Al Pacino, James Caan, Diane Keaton", 1972, 1));
        actionMovies.add(new Movie("Heat",  host + "action2.jpg", "Tim Robbins, Morgan Freeman, Bob Gunton, William Sadler", 1994, 2));
        actionMovies.add(new Movie("Inception", host + "action3.jpg", "Liam Neeson, Ralph Fiennes, Ben Kingsley, Caroline Goodall", 1993, 3));
        actionMovies.add(new Movie("Kill Bill: Vol 1.", host + "action4.jpg", "Robert De Niro, Cathy Moriarty, Joe Pesci, Frank Vincent", 1980, 4));
        actionMovies.add(new Movie("Gladiator", host + "action5.jpg", "Humphrey Bogart, Ingrid Bergman, Paul Henreid, Claude Rains", 1942, 5));
        actionMovies.add(new Movie("Saving Private Ryan", host + "action6.jpg", "Jack Nicholson, Louise Fletcher, Michael Berryman, Peter Brocco", 1975, 6));
        actionMovies.add(new Movie("Terminator 2 - Judgement Day", host + "action7.jpg", "Clark Gable, Vivien Leigh, Thomas Mitchell, Barbara O'Neil", 1939, 7));
        actionMovies.add(new Movie("The Bourne Ultimatum", host + "action8.jpg", "Orson Welles, Joseph Cotten, Dorothy Comingore, Agnes Moorehead", 1941, 8));
        actionMovies.add(new Movie("The Dark Knoght Rises",host + "action9.jpg", "Judy Garland, Frank Morgan, Ray Bolger, Bert Lahr", 1939, 9));
        actionMovies.add(new Movie("The Matrix",host + "action10.jpg", "Peter O'Toole, Alec Guinness, Anthony Quinn, Jack Hawkins", 1962, 10));

    }
}
