package es.codeurjc.web.entities;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity(name="UserTable")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String username;
    private String password;
    @OneToMany(mappedBy = "author",cascade=CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviews;
    private String role;

    protected User(){

    }

    public User(String username, String password, String role) {
        super();
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setAuthor(this);
    }
 
    public void removeReview(Review review) {
        reviews.remove(review);
        review.setAuthor(null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    

    
    
}