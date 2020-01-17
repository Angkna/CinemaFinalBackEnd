package cinema.data;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Objects;
import java.util.OptionalInt;

public class Person {
	
	private String name;
	private LocalDate birthdate;
	
	public Person(String name, LocalDate birthdate) {
		super();
		this.name = name;
		this.birthdate = birthdate;
	}

	public Person(String name) {
		this(name, null);
		// ou bien
		//super();
		//this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	@Override
	public String toString() {
		return name + " (" + Objects.toString(birthdate, "unknown") + ")" ;
	}
	
	// ne fonctionne pas sur les années precedentes et suivante à 2020 mais pour info :
	//calcul age
	// public int getAge() {       	//par defaut il prend la date de ajd // 
//	public OptionalInt getAge() {         //on ajoute l'optional  
//		if(Objects.isNull(birthdate)) {
//			return OptionalInt.empty(); 		//resultat vide
//		}								// on pourrait rajouter un else pour tout 
//										//le reste mais ce n'est pas obligatoire car le return fait sortir de la bouche
//		LocalDate today = LocalDate.now();
//		LocalDate birthday = LocalDate.of(
//					today.getYear(), 
//					birthdate.getMonthValue(), 
//					birthdate.getDayOfMonth());
//		int deltaYear = today.getYear() - birthdate.getYear();
//		if (today.compareTo(birthday) < 0) {;
//			deltaYear--;
//			// ou bien 
//			//--deltaYears	
//		}	
//		return OptionalInt.of(deltaYear);
	
	
	// pour prendre en charge les années bi
	public OptionalInt getAge() {         //on ajoute l'optional  
		if(Objects.isNull(birthdate)) {
			return OptionalInt.empty(); 		//resultat vide
		}								
									
		LocalDate todayFull = LocalDate.now();
		MonthDay birthday = MonthDay.of(
					birthdate.getMonthValue(), 
					birthdate.getDayOfMonth());
			MonthDay today = MonthDay.now();
		int deltaYear = todayFull.getYear() - birthdate.getYear();
		if (today.compareTo(birthday) < 0) {;
			deltaYear--;
			// ou bien 
			//--deltaYears	
		}	
		return OptionalInt.of(deltaYear);

	}
	
	//TODO equals + hashCode
}
