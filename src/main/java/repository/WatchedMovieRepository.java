package repository;

import model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface WatchedMovieRepository extends CrudRepository <Movie, Integer> {

}
