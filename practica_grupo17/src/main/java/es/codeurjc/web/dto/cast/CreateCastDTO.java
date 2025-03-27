package es.codeurjc.web.dto.cast;

import java.util.List;

public class CreateCastDTO {

    private String name;
    private String biography;
    private String birthDate;
    private String originCountry;
    private List<Long> movieIds;

    public CreateCastDTO() {}

    public CreateCastDTO(String name, String biography, String birthDate,
                         String originCountry, List<Long> movieIds) {
        this.name = name;
        this.biography = biography;
        this.birthDate = birthDate;
        this.originCountry = originCountry;
        this.movieIds = movieIds;
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

    public List<Long> getMovieIds() {
        return movieIds;
    }

    public void setMovieIds(List<Long> movieIds) {
        this.movieIds = movieIds;
    }
}
