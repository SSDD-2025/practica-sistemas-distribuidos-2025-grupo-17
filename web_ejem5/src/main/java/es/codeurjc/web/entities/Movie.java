package es.codeurjc.web.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    @ManyToMany
    private List<Cast> cast;
    private String argument;
    private int year;
    private String trailer;

    // Constructor  JPA
    protected Movie() {}

    // Constructor
    public Movie(String name, String argument, int year, List<Cast> cast, String trailer) {
        this.name = name;
        this.argument = argument;
        this.year = year;
        this.cast=cast;
        this.trailer=trailer;
    }

    // Getters Setters
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

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

}
