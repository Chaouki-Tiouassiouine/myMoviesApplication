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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {
    //methode voor het ophalen van een film, toevoegen van een film en toevoegen van een bekeken film naar een aparte repository

    @Autowired
    private WatchedMovieRepository watchedMovieRepository;

    @Autowired
    private MovieRepository movieRepository;

    public MovieController() {
    }

    //Met deze methode zorg ik ervoor dat ik alle movies ophaal, zowel gekeken als niet gekeken
    @RequestMapping(method = RequestMethod.GET, value = "/getMovieList")
    public List<Movie> movieList() {
        List<Movie> list = new ArrayList<>();
        movieRepository.findAll().forEach(list::add);
        watchedMovieRepository.findAll().forEach(list::add);
        return list;
    }

    //Code is nog niet bruikbaar, wil hier graag een methode maken met een connectie met IMBD API
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
//                JSONArray weather = jsonObj.getJSONArray("movie");
//                int movieId =  (int) movie.getJSONObject(0).get("id");
//            }
//        } catch (IOException e) {
//            System.out.println("Geen movies beschikbaar op het moment van de externe database");
//        }
//    }
//
//    private String convertStreamToString(InputStream stream) {
//    }

    //Met deze methode voeg ik een movie toe, zie de validaties voor datum toegevoegd en check of suitable age wel daadwerkelijk een getal is tussen de 0 en 100
    @RequestMapping(method = RequestMethod.POST, value = "/addManuallyMovie")
    public Movie addManuallyMovie(@RequestBody Movie movie) {
        if (Validators.suitableMovieAgeMatcher(movie.getSuitableAgeMovie()) &&
                (movie.getMovieDate().isBefore(LocalDate.now()))) {
            return movieRepository.save(movie);
        }
        else
        return null;
    }

    //Met deze methode voeg ik een vinkje toe aan movie die ik heb gezien
    @RequestMapping(method = RequestMethod.POST, value = "/addWatchedMovie")
    public Movie addWatchedMovie(@RequestBody Movie movie) {
        if (movie.isMovieWatched()) {
            return watchedMovieRepository.save(movie);
        }
        else
        return null;
    }

    //met deze methode wil ik de optie hebben om een film die handmatig is toegevoegd ook te kunnen wijzigen
    @RequestMapping(value = "/changeMovie", method = RequestMethod.POST)
    public Movie changeMovie(@RequestBody Movie movie) {
        movieRepository.delete(movie);
        return movieRepository.save(movie);
    }

    //Met deze methode wil ik dat de movie verwijderd kan worden
    @RequestMapping(value = "/deleteMovie", method = RequestMethod.POST)
    public void deleteMovie(@RequestBody Movie movie) {
        movieRepository.delete(movie);
        watchedMovieRepository.delete(movie);
    }
}
