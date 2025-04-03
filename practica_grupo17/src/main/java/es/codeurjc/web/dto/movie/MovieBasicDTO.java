package es.codeurjc.web.dto.movie;

public class MovieBasicDTO {

    private Long id;
    private String name;
    private int year;
    private String argument;

    public MovieBasicDTO() {
    }

    public MovieBasicDTO(Long id, String name, int year, String argument) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.argument = argument;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public String getArgument() {
        return argument;
    }
}
