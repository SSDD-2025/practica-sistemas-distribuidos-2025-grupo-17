package es.codeurjc.web.entities;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    @ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

    protected User(){

    }

    public User(String username, String password,String... roles) {
        super();
        this.username = username;
        this.password = password;
        this.roles = List.of(roles);
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(String... roles) {
        this.roles = List.of(roles);
    }

    

    
    
}