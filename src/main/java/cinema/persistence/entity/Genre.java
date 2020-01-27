package cinema.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "genres")
public class Genre {
	
	private Integer id;
	private String genre;
	
	public Genre() {
		super();
	}

	public Genre(String genre) {
		this(null, genre);
	}

	public Genre(Integer id, String genre) {
		super();
		this.id = id;
		this.genre = genre;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_genre")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return  genre;
	}
	
	
}
