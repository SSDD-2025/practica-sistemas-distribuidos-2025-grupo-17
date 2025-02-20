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

    @ManyToMany(mappedBy = "cast")
    private List<Movie> movies;

    private String biography;
    private Date birthDate;
    private String workField; // Actor, director, guionista, etc.

    @ElementCollection
    private List<String> awards;

    private String originCountry;

    // Constructor vacío requerido por JPA
    protected Cast() {}

    // Constructor con parámetros
    public Cast(String name, String biography, Date birthDate, String workField, String originCountry) {
        this.name = name;
        this.biography = biography;
        this.birthDate = birthDate;
        this.workField = workField;
        this.originCountry = originCountry;
    }

    // Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
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

    public List<String> getAwards() {
        return awards;
    }

    public void setAwards(List<String> awards) {
        this.awards = awards;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    @Override
    public String toString() {
        return "Cast{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", biography='" + biography + '\'' +
                ", birthDate=" + birthDate +
                ", workField='" + workField + '\'' +
                ", originCountry='" + originCountry + '\'' +
                ", awards=" + awards +
                '}';
    }
}
