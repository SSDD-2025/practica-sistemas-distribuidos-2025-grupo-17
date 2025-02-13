import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
 	
	private String name;
    private List<Cast> cast; //not implented yet
    private Image image;
    private String argument;
    private Date year;
    private float mark;
    private List<Review> review; //not implemented yet
    private String platform;
    private Video trailer;
    private int pegi;
    private int duration;
    private List<String> prize;
    private String originCountry;
    private List<String> genres;

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public List<String> getReview() {
        return review;
    }

    public void setReview(List<String> review) {
        this.review = review;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Video getTrailer() {
        return trailer;
    }

    public void setTrailer(Video trailer) {
        this.trailer = trailer;
    }

    public int getPegi() {
        return pegi;
    }

    public void setPegi(int pegi) {
        this.pegi = pegi;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<String> getPrize() {
        return prize;
    }

    public void setPrize(List<String> prize) {
        this.prize = prize;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }


	protected Movie() {
		// Used by JPA
	}

	public Movie(String firstName, String lastName) { //Not implemented yet
		this.firstName = firstName;
		this.lastName = lastName;
	}


	@Override
	public String toString() { //Not implemented yet
		return String.format("Movie[id=%d, firstName='%s', lastName='%s']",
				id, firstName, lastName);
	}
}
