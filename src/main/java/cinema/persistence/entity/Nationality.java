package cinema.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Nationality {
	private Integer id;
	private String nationality;
	
	public Nationality() {
		super();
	}
	
	public Nationality(String nationalitiy) {
		this(null, nationalitiy);
	}
	
	public Nationality(Integer id, String nationality) {
		super();
		this.id = id;
		this.nationality = nationality;
	}
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	

}
