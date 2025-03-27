package es.codeurjc.web.dto.review;

public class CreateReviewDTO {

    private String title;
    private String text;
    private Long movieId;

    public CreateReviewDTO() {}

    public CreateReviewDTO(String title, String text, Long movieId) {
        this.title = title;
        this.text = text;
        this.movieId = movieId;
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

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
