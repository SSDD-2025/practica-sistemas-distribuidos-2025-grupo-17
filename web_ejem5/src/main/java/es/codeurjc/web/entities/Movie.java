package es.codeurjc.web.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "movie_cast",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "cast_id")
    )
    private List<Cast> cast;

    private String argument;
    private int year;
    private float mark;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    private String platform;
    private int pegi;
    private int duration;

    @ElementCollection
    private List<String> prize;

    private String originCountry;

    @ElementCollection
    private List<String> genres;

    // Constructor  JPA
    protected Movie() {}

    // Constructor
    public Movie(String name, String argument, int year, float mark, String platform, int pegi, int duration, String originCountry) {
        this.name = name;
        this.argument = argument;
        this.year = year;
        this.mark = mark;
        this.platform = platform;
        this.pegi = pegi;
        this.duration = duration;
        this.originCountry = originCountry;
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

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getPegi() {
        return pegi;
    }

    public void setPegi(int pegi) {
        this.pegi = pegi;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<String> getPrize() {
        return prize;
    }

    public void setPrize(List<String> prize) {
        this.prize = prize;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", argument='" + argument + '\'' +
                ", year=" + year +
                ", mark=" + mark +
                ", platform='" + platform + '\'' +
                ", pegi=" + pegi +
                ", duration=" + duration +
                ", originCountry='" + originCountry + '\'' +
                ", genres=" + genres +
                '}';
    }
}
