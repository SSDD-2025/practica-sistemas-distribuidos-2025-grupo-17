package es.codeurjc.web.entities;
import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Review {

	@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
	private long idReview;
 	
	private String title;
    private String text;
    
    //Constructor JPA
    //protected Review() {}

    public Review(String title,String text){
        this.title=title;
        this.text=text;
    }

    public long getId() {
        return idReview;
    }

    public void setId(long id) {
        this.idReview = id;
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
