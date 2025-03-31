package es.codeurjc.web.dto.movie;

import java.util.List;

public class CreateMovieDTO {

    private String name;
    private String argument;
    private int year;
    private String trailer;
    private List<Long> castIds;

    public CreateMovieDTO() {}

    public CreateMovieDTO(String name, String argument, int year, String trailer, List<Long> castIds) {
        this.name = name;
        this.argument = argument;
        this.year = year;
        this.trailer = trailer;
        this.castIds = castIds;
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

    public List<Long> getCastIds() {
        return castIds;
    }

    public void setCastIds(List<Long> castIds) {
        this.castIds = castIds;
    }
}
