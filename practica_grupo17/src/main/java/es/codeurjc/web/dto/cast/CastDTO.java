package es.codeurjc.web.dto.cast;

import java.util.List;
import es.codeurjc.web.dto.movie.MovieBasicDTO;


public class CastDTO {

    private Long id;
    private String name;
    private String biography;
    private String birthDate;
    private String originCountry;
    private List<MovieBasicDTO> movies;

    public CastDTO() {}

    public CastDTO(Long id, String name, String biography, String birthDate,
                   String originCountry, List<MovieBasicDTO> movies) {
        this.id = id;
        this.name = name;
        this.biography = biography;
        this.birthDate = birthDate;
        this.originCountry = originCountry;
        this.movies = movies;
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public List<MovieBasicDTO> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieBasicDTO> movies) {
        this.movies = movies;
    }
}
