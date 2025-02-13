//Prueba comentario Git

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
public class Cast {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
 	
	private String name;
    private List<Movie> movies; //not implented yet
    private Image image;
    private String biography;
    private Date birthDate;
    private String workField; //can be an actor, director...
    private List<String> awards;
    private String originCountry;

    // Getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public Image getImage() {
        return image;
    }

    public String getBiography() {
        return biography;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getWorkField() {
        return workField;
    }

    public List<String> getAwards() {
        return awards;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setWorkField(String workField) {
        this.workField = workField;
    }

    public void setAwards(List<String> awards) {
        this.awards = awards;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }




	protected Cast() {
		// Used by JPA
	}

	public Cast(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}


	@Override
	public String toString() {
		return String.format("Customer[id=%d, firstName='%s', lastName='%s']",
				id, firstName, lastName);
	}
}
