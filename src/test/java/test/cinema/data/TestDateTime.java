package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;

class TestDateTime {

	@Test
	void testParseFenchDate() {
		String dateStr = "16/02/2020";
		LocalDate date = LocalDate.parse(dateStr,
				DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		System.out.println(date);
		assertAll(							//pour avoir toute les v�rifications sans blocage des le 1er probleme
				()-> assertEquals(15, date.getDayOfMonth(), "day"),						// besoin de passer par fonction lambda dans se contexte
				()-> assertEquals(1, date.getMonthValue(), "month"),
				()-> assertEquals(2020, date.getYear(), "year") 
				);
	}

	@Test
	void testFormatDate() {
		LocalDate date = LocalDate.now();    //date du jour informatique 
		var format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		var format2 = DateTimeFormatter.ofPattern("eeee dd MMMM yyyy");
		var format3 = DateTimeFormatter.ofPattern("eeee dd MMMM yyyy" , 
				Locale.US);		//pour mettre dans la langue d'un autre pays
		var format4 = DateTimeFormatter.ofPattern("eeee dd MMMM yyyy" , 
				new Locale("es", "es"));						//pour mettre dans la langue d'un pays non pr� enregistr�
		var format5 = DateTimeFormatter.ofPattern("eeee dd MMMM yyyy" , 
				new Locale("lv", "lv"));
		
		
		var format6 = DateTimeFormatter.ofPattern("eeee dd MMMM yyyy" , 
				new Locale("rs", "rs"));
		System.out.println(date.format(format1));
		System.out.println(date.format(format2));
		System.out.println(date.format(format3));
		System.out.println(date.format(format4));
		System.out.println(date.format(format5));
		System.out.println(date.format(format6));
	}
	
	@Test
	void testFormatDate2() {
		LocalDate date = LocalDate.now();    //date du jour informatique 
		var formats = List.of (
				
				DateTimeFormatter.ofPattern("dd/MM/yyyy"),
				DateTimeFormatter.ofPattern("eeee dd MMMM yyyy"),
				DateTimeFormatter.ofPattern("eeee dd MMMM yyyy" ,
						Locale.US),		//pour mettre dans la langue d'un autre pays
				DateTimeFormatter.ofPattern("eeee dd MMMM yyyy" , 
						new Locale("es", "es")),						//pour mettre dans la langue d'un pays non pré enregistré
				DateTimeFormatter.ofPattern("eeee dd MMMM yyyy" , 
						new Locale("lv", "lv")),
				DateTimeFormatter.ofPattern("eeee dd MMMM yyyy" , 
						new Locale("ru", "ru")),
				DateTimeFormatter.ofPattern("eeee dd MMMM yyyy" , 
						new Locale("ja", "ja")),
				DateTimeFormatter.ofPattern("eeee dd MMMM yyyy" , 
						new Locale("in", "in"))
						);
				
			formats.forEach(f -> System.out.println(date.format(f)));		
	}
	
		@Test
		void testInde() {
			var date = LocalDate.now();
			Arrays.stream(Locale.getAvailableLocales())
				.filter(p -> p.getCountry().equals("IN"))
				//.forEach(System.out::println);
				.map(l -> date.format(
						DateTimeFormatter.ofPattern("eeee dd MMMM yyyy" , l)))
						
				.forEach (System.out::println);
		}
				
		@Test
		void tourDuMonde80jours() {
			var dtHere = LocalDateTime.now();
			System.out.println("here : " + dtHere);
			var dtNY = LocalDateTime.now(ZoneId.of("America/New_York"));
			System.out.println("NY :" + dtNY);
			var dtNZ = LocalDateTime.now(ZoneId.of("Pacific/Auckland"));
			System.out.println("New Zealand : "+ dtNZ);
			var dtMidway = LocalDateTime.now(ZoneId.of("Pacific/Midway"));
			System.out.println("Midway: " + dtMidway);
			
			var fmt = DateTimeFormatter.ofPattern("hh:mm:ss dd/MM/yyyy");
			System.out.println("A Midway il est : " + dtMidway.format(fmt));
	}
		
}
	

