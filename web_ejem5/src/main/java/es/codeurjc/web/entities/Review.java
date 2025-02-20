package es.codeurjc.web.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Review {

    public enum ReviewType {
        RATING_ONLY, // Solo nota
        FULL_REVIEW  // Reseña con título y cuerpo
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private ReviewType type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Usuario que hace la review

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie; // Película que se está valorando

    private float rating;  // Nota de la película

    private String title;  // Solo si es reseña
    private String body;   // Solo si es reseña

    private int likes = 0; // Número de likes (inicializa en 0)

    @Temporal(TemporalType.TIMESTAMP)
    private Date date; // Fecha de la review

    // Constructor vacío requerido por JPA
    protected Review() {}

    // Constructor para review solo con nota
    public Review(User user, Movie movie, float rating) {
        this.type = ReviewType.RATING_ONLY;
        this.user = user;
        this.movie = movie;
        this.rating = rating;
        this.date = new Date();
    }

    // Constructor para review completa
    public Review(User user, Movie movie, float rating, String title, String body) {
        this.type = ReviewType.FULL_REVIEW;
        this.user = user;
        this.movie = movie;
        this.rating = rating;
        this.title = title;
        this.body = body;
        this.likes = 0;
        this.date = new Date();
    }

    // Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ReviewType getType() {
        return type;
    }

    public void setType(ReviewType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Método para dar like a una review
    public void addLike() {
        this.likes++;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", type=" + type +
                ", user=" + user.getUsername() +
                ", movie=" + movie.getName() +
                ", rating=" + rating +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", likes=" + likes +
                ", date=" + date +
                '}';
    }
}
