package es.codeurjc.web.entities;

import jakarta.persistence.*;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity(name="castTable")
public class Cast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String biography;
    private String birthDate;
    private String originCountry;
    @ManyToMany(mappedBy = "cast")
    private List<Movie> movies;
    @Lob
    private Blob castImage;

    // Constructor for JPA
    protected Cast() {
    }

    // Constructor
    public Cast(String name, String biography, String birthDate, String originCountry) {
        super();
        this.name = name;
        this.biography = biography;
        this.birthDate = birthDate;
        this.originCountry = originCountry;
        movies=new ArrayList<>();
    }

    public boolean containsMovie(Movie movie){
        return movies.contains(movie);
    }

    public void addMovie(Movie newMovie) {
        movies.add(newMovie);
        if (!newMovie.containsCast(this)){
            newMovie.addCast(this);
        }
    }
 
    public void removeMovie(Movie deleteMovie) {
        movies.remove(deleteMovie);
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public Blob getCastImage() {
        return castImage;
    }

    public void setCastImage(Blob castImage) {
        this.castImage = castImage;
    }
}
