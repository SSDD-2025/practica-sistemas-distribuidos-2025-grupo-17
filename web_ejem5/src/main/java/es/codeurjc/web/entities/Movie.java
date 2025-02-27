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
    @OneToMany(mappedBy = "movie",cascade=CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviews;

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

    public void addReview(Review review) {
        reviews.add(review);
        review.setMovie(this);
    }
 
    public void removeReview(Review review) {
        reviews.remove(review);
        review.setMovie(null);
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
