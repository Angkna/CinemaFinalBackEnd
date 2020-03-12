package cinema.persistence.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table( name = "users", 
		uniqueConstraints={
				@UniqueConstraint(columnNames={"userName"}),
				@UniqueConstraint(columnNames={"email"})
				}
		)
public class User {
	
	private Integer idUser;
	private String userName;
	private String password;
	private String email;
	private String role;
	private Set<Movie> movieLiked;
	
	public User() {
		super();
	}

	public User(String userName, String password, String email, String role) {
		super();
		this.idUser = null;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.role = role;
		this.movieLiked = null;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	
	@Column(nullable = false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(nullable = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(nullable = true)
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@ManyToMany
	@JoinTable(
		name="likedmovie",
		joinColumns= @JoinColumn(name="id_user"),
		inverseJoinColumns= @JoinColumn(name="id_movies")
	)
	public Set<Movie> getMovieLiked() {
		return movieLiked;
	}

	public void setMovieLiked(Set<Movie> movieLiked) {
		this.movieLiked = movieLiked;
	}

	
}
