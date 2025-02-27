package es.codeurjc.web.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
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

    // Constructor for JPA
    protected Cast() {
    }

    // Constructor
    public Cast(String name, String biography, String birthDate, String originCountry, List<Movie> movies) {
        super();
        this.name = name;
        this.biography = biography;
        this.birthDate = birthDate;
        this.originCountry = originCountry;
        this.movies = movies;
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
}
