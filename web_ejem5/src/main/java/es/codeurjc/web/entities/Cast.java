package es.codeurjc.web.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Cast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String biography;
    private Date birthDate;
    private String workField; // Actor, director, guionista, etc.
    private String originCountry;
    private List<Movie> movies;

    // Constructor for JPA
    protected Cast() {}

    // Constructor
    public Cast(String name, String biography, Date birthDate, String workField, String originCountry, List<Movie> movies) {
        this.name = name;
        this.biography = biography;
        this.birthDate = birthDate;
        this.workField = workField;
        this.originCountry = originCountry;
        this.movies=movies;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getWorkField() {
        return workField;
    }

    public void setWorkField(String workField) {
        this.workField = workField;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }
}
