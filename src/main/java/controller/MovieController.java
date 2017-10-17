package controller;

import model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import repository.MovieRepository;
import repository.WatchedMovieRepository;
import utils.Validators;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController { //methode voor het ophalen van een film, toevoegen van een film en afvinken van een film

    @Autowired
    private WatchedMovieRepository watchedMovieRepository;

    @Autowired
    private MovieRepository movieRepository;

    public MovieController() {
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getMovieList")
    //Met deze methode zorg ik ervoor dat ik alle movies ophaal
    public List<Movie> movieList() {
        List<Movie> list = new ArrayList<>();
        movieRepository.findAll().forEach(list::add);
        return list;
    }

//    private String apiURL = "http://img.omdbapi.com/?apikey=[yourkey]&";
//    public void searchForMovie() {
//        try {
//            // Dit is de API request waarmee ik films wil ophalen van IMDB
//            URL url = new URL(apiURL);
//
//            try (InputStream stream = url.openStream()) {
//                String output = convertStreamToString(stream);
//
//                // Converteer teruggekeerde string naar JSON
//                JSONObject jsonObj = new JSONObject(output);
//                JSONObject main = jsonObj.getJSONObject("main");
//                JSONArray weather = jsonObj.getJSONArray("weather");
//                int weatherId =  (int) weather.getJSONObject(0).get("id");
//            }
//        } catch (IOException e) {
//            System.out.println("Geen movies beschikbaar op het moment van de externe database");
//        }
//    }
//
//    private String convertStreamToString(InputStream stream) {
//    }


    @RequestMapping(method = RequestMethod.POST, value = "/addManuallyMovie")
    //Met deze methode voeg ik een movie toe, zie de validaties voor datum toegevoegd en check of suitable age wel daadwerkelijk een getal is tussen de 0 en 100
    public Movie addManuallyMovie(@RequestBody Movie movie) {
        if (Validators.suitableMovieAgeMatcher(movie.getSuitableAgeMovie()) &&
                (movie.getMovieDate().isBefore(LocalDate.now()))) {
            return movieRepository.save(movie);
        }
        else
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addWatchedMovie")
    //Met deze methode voeg ik een vinkje toe aan movie die ik heb gezien
    public Movie addWatchedMovie(@RequestBody Movie movie) {
        if (movie.isMovieWatched()) {
            return watchedMovieRepository.save(movie);
        }
        else
        return null;
    }
}
