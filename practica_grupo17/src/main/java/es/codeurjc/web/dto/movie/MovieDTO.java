package es.codeurjc.web.dto.movie;

import java.util.List;
import es.codeurjc.web.dto.cast.CastBasicDTO;
import es.codeurjc.web.dto.review.ReviewDTO;


public class MovieDTO {

    private Long id;
    private String name;
    private String argument;
    private int year;
    private String trailer;
    private List<CastBasicDTO> cast;
    private List<ReviewDTO> reviews;

    public MovieDTO() {}

    public MovieDTO(Long id, String name, String argument, int year, String trailer,
                    List<CastBasicDTO> cast, List<ReviewDTO> reviews) {
        this.id = id;
        this.name = name;
        this.argument = argument;
        this.year = year;
        this.trailer = trailer;
        this.cast = cast;
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<CastBasicDTO> getCast() {
        return cast;
    }

    public void setCast(List<CastBasicDTO> cast) {
        this.cast = cast;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }
}
