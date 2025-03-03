package es.codeurjc.web.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long idReview;
 	
    @ManyToOne
    private Movie movie;
	private String title;
    private String text;
    @ManyToOne
    private User author;
    
    //Constructor JPA
    protected Review() {}

    public Review(String title,String text,Movie movie,User author){
        super();
        this.title=title;
        this.text=text;
        this.movie=movie;
        this.author=author;
    }

    public long getId() {
        return idReview;
    }

    public void setId(long id) {
        this.idReview = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
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
    
}
