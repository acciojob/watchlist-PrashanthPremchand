package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;

    public MovieRepository(){
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
    }

    public void saveMovie(Movie movie){
        // your code here
        String movieName = movie.getName();
        movieMap.put(movieName, movie);
    }

    public void saveDirector(Director director){
        // your code here
        directorMap.put(director.getName(), director);
    }

    public void saveMovieDirectorPair(String movie, String director){
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
            // your code here
            List<String> currentMovies = new ArrayList<>();
            if(directorMovieMapping.containsKey(director)) currentMovies = directorMovieMapping.get(director);
            currentMovies.add(movie);
            directorMovieMapping.put(director, currentMovies);
        }
    }

    public Movie findMovie(String movie){
        // your code here
        return movieMap.getOrDefault(movie, null);
    }

    public Director findDirector(String director){
        // your code here
        return directorMap.getOrDefault(director, null);
    }

    public List<String> findMoviesFromDirector(String director){
        // your code here
        return directorMovieMapping.getOrDefault(director, new ArrayList<>());
    }

    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirector(String director){
        // your code here
        List<String> movies = new ArrayList<>();
        if(directorMovieMapping.containsKey(director)) {
            movies = directorMovieMapping.get(director);
            for(String movie: movies){
                if(movieMap.containsKey(movie)) movieMap.remove(movie);
            }
            directorMovieMapping.remove(director);
        }

        if(directorMap.containsKey(director))directorMap.remove(director);
    }

    public void deleteAllDirector(){
        // your code here
        Set<String> movieSet = new HashSet<>();
        for(String director: directorMovieMapping.keySet()){
            movieSet.addAll(directorMovieMapping.get(director));
        }
        for(String movie: movieSet){
            if(movieMap.containsKey(movie)) movieMap.remove(movie);
        }
    }
}