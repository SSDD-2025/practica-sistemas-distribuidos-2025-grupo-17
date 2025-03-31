package es.codeurjc.web.dto.review;

public class ReviewDTO {

    private Long id;
    private String title;
    private String text;
    private String authorUsername;
    private Long movieId;

    public ReviewDTO() {}

    public ReviewDTO(Long id, String title, String text, String authorUsername, Long movieId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.authorUsername = authorUsername;
        this.movieId = movieId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
