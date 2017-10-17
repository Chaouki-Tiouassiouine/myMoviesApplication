package model;

import model.enums.MovieGenre;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Movie {

    private String movieName;
    private MovieGenre genre;
    private LocalDate movieDate;
    private String suitableAgeMovie;
    private boolean movieWatched;

    public String getMovieName() { return movieName; }

    public void setMovieName(String movieName) { this.movieName = movieName; }

    public MovieGenre getGenre() { return genre; }

    public void setGenre(MovieGenre genre) { this.genre = genre; }

    public LocalDate getMovieDate() { return movieDate; }

    public void setMovieDate(LocalDate movieDate) { this.movieDate = movieDate; }

    public String getSuitableAgeMovie() { return suitableAgeMovie; }

    public void setSuitableAgeMovie(String suitableAgeMovie) { this.suitableAgeMovie = suitableAgeMovie; }

    public boolean isMovieWatched() { return movieWatched; }

    public void setMovieWatched(boolean movieWatched) { this.movieWatched = movieWatched; }
}
