package cinema.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Act {
		
	private ActId id;
	private String role;
	
	public Act() {
		super();
	}
	
	public Act(String role) {
		this(null, role);
	}

	public Act(ActId id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	@Id
	public ActId getId() {
		return id;
	}

	public void setId(ActId id) {
		this.id = id;
	}
	
	@Column(name = "role")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}


//Bradley Cooper			Chris Kyle				American Sniper
//							Rocket (voice) 			Infinity war 
//							Phil					VBT

//Joaquin Phoenix     		Arthur Fleck			Joker
//
//Gene Hackman   			Little Bill Daggett 	Impittoyable
//
//Morgan Freeman 			Ned Logan				Impittoyable

//Brie Larson				Caroline Marvel			Marvel 


//////////////INFO 6 DEGRES DE KEVIN BAKON ///////////////////
//kevin bacon joue dans appolo 13 avec Tom Hanks
//tom joue dans PENTAGON PAPERS avc MERYL STREEP
//meryl joue dans mamma mia avec COLIN FIRTH
//COLIN FIRTH joue dans LE DISCOURS D'UN ROI avec HELENA BONHAM CARTER
//helena joue dans OCEAN'S 8 avec CATE BLANCHETT 
//kate joue dans lE SEIGNEUR DES ANNEAUX  avec VIGGO MORTENSEN
//viggo joue dans CAPTAIN FANTASTIC avec FRANK LANGELLA