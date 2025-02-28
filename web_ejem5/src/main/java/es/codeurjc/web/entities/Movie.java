package es.codeurjc.web.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name="MovieTable")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @ManyToMany(cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
    @JoinTable(
        name = "movie_cast",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "cast_id")
    )
    private List<Cast> cast;
    private String argument;
    @Column(name = "movie_year")
    private int year;
    private String trailer;
    @OneToMany(mappedBy = "movie",cascade=CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviews;

    // Constructor JPA
    protected Movie() {
    }

    // Constructor
    public Movie(String name, String argument, int year, String trailer) {
        super();
        this.name = name;
        this.argument = argument;
        this.year = year;
        this.trailer = trailer;
        cast=new ArrayList<>();
        reviews=new ArrayList<>();
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setMovie(this);
    }
 
    public void removeReview(Review review) {
        reviews.remove(review);
        review.setMovie(null);
    }

    public boolean containsCast(Cast containsCast){
        return cast.contains(containsCast);
    }

    public void addCast(Cast newCast) {
        cast.add(newCast);
        if (!newCast.containsMovie(this)){
            newCast.addMovie(this);
        }
    }
 
    public void removeCast(Cast deleteCast) {
        cast.remove(deleteCast);
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
