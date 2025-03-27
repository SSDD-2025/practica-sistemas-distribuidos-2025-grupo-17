package es.codeurjc.web.dto.cast;

public class CastBasicDTO {

    private Long id;
    private String name;
    private String birthDate;
    private String originCountry;

    public CastBasicDTO() {}

    public CastBasicDTO(Long id, String name, String birthDate, String originCountry) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.originCountry = originCountry;
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
}
