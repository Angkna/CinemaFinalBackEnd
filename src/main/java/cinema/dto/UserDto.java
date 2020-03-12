package cinema.dto;

import java.util.List;

public class UserDto {
	private Integer idUser;
	private String userName;
	private String password;
	private String email;
	private String role;
	private List<MovieLight> movieLiked;
	
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<MovieLight> getMovieLiked() {
		return movieLiked;
	}
	public void setMovieLiked(List<MovieLight> movieLiked) {
		this.movieLiked = movieLiked;
	}
}
