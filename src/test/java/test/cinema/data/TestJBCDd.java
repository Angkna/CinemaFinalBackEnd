package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import cinema.data.Movie;
import cinema.data.Person;

class TestJBCDd {

	static DataSource ds;			//le static permet de faire qu'il n'y a qu1 unique objet comme ça
	
	@BeforeAll
	static void initDataSource () {
		PGSimpleDataSource pgds = new PGSimpleDataSource();								//permet la connection
		String host = "localhost";
		String dbname = "postgres";				//la base de donné sur lequel on travail
		int port = 5432;									// le port car peut y avoir plusieurs serveur sur une machine unique
		String user = "cinema";
		String password = "password";
		pgds.setURL(
				"jdbc:postgresql://"
				+ host+ ":" + port + "/" + dbname);
		pgds.setUser(user);
		pgds.setPassword(password);
		//System.out.println(pgds.getUrl());;
		ds = pgds;
	}
	
	
	@Test
	void testLireFilms() throws SQLException {
		List<Movie> listMovie = new ArrayList<Movie>();
		String sql = "select * from film";
		try (
			Connection conn = ds.getConnection(); 			//création de la connexion // le try limite l'utilisation de la ressource sur ces lignes)
			Statement request = conn.createStatement();
			ResultSet res = request.executeQuery(sql);			//sorte de select
		) {
			while (res.next()) {
				String title = res.getString("titre");
				int year = res.getInt("annee");
				int duration = res.getInt("duree");
				//System.out.println(title+year+duration);
//				System.out.println("Titre : " +title);
//				System.out.println("Année : " + year);
//				System.out.println("Durée : " + duration + " min");
//				System.out.println("/");
				//Add movie
				listMovie.add(new Movie (title , year, duration));
					
										
			}
		} //conn.close();				//fermeture de la connexion	// PLUS BESOIN grace au try et aux accolades
		
		System.out.println(listMovie);
	}

	@Test
	void testLireFilms2() throws SQLException {
		var listMovie = new TreeSet<Movie>(
					Comparator.comparing(Movie::getTitle)
					.thenComparing(Movie::getYear));
		
		String sql = "select * from film";
		try (
			Connection conn = ds.getConnection(); 			//création de la connexion // le try limite l'utilisation de la ressource sur ces lignes)
			Statement request = conn.createStatement();
			ResultSet res = request.executeQuery(sql);			//sorte de select
		) {
			while (res.next()) {
				String title = res.getString("titre");
				int year = res.getInt("annee");
				int duration = res.getInt("duree");
				
				listMovie.add(new Movie (title , year, duration));						
			}
		} //conn.close();				//fermeture de la connexion	// PLUS BESOIN grace au try et aux accolades
		
		System.out.println(listMovie);
			for (var m: listMovie) {
				System.out.println(m + " de durée " + m.getDuration()+ "mn");
			}
		var totalDuration = listMovie.stream()
			.mapToInt(Movie::getDuration)
			.sum();
		System.out.println(totalDuration + " minutes totales");
	}
	
	@Test
	void testLireFilmsFiltre() throws SQLException {
		var listMovie = new TreeSet<Movie>(
				Comparator.comparing(Movie::getTitle)
				.thenComparing(Movie::getYear));
	
		String sql = "select * from film where duree >= ?";			//?=joker qui correspond au index réponse
		int durationThreshold = 120;
		//sql += durationThreshold;  //interdit 
		System.out.println(sql);
		try (
				Connection conn = ds.getConnection();
				PreparedStatement request = conn.prepareStatement(sql); // pour quand donné yutilisateur attendu
		) {
			request.setInt(1, durationThreshold);			//le 1 fait réfrence à la 1re ligne du tableau en sql
			try (ResultSet res = request.executeQuery()) {	
				while (res.next()) {
					String title = res.getString("titre");
					int year = res.getInt("annee");
					int duration = res.getInt("duree");

					listMovie.add(new Movie (title , year, duration ));	
				}
			}   //=resclose
		}  //=request/conn.close()
		System.out.println(listMovie);
		//check all movies with duration >= durationThreshold
		assertAll(listMovie.stream()
			.map(m -> () -> assertTrue(m.getDuration() >= durationThreshold)));
				//.map(m -> () -> assertTrue(m.getDuration() >= 150, m.getTitle())));
		
	}

	@Test
	void testLireFilmsFiltre2problemeInjection() {
		String sql = "select * from film where titre = '";
		String sql2 = "'";
		String title = "Joker' or true or titre='";
		sql += title + sql2;			//MAUVAIS ! ne jamais mélanger code entre résulats utilisateur et application
		
		System.out.println(sql);
	}
	
	@Test
	void testLireFilmsFiltre3InjectionNotAllowed() throws SQLException {
		String sql = "select * from film where titre = ?";
		String title = "Joker' or true or titre='";
		try (
				Connection conn = ds.getConnection();
				PreparedStatement request = conn.prepareStatement(sql)
		){
			request.setString(1, title);
			try (ResultSet res = request.executeQuery()) {
				while(res.next() ) {
					System.out.println(res.getString("Titre"));
				}
			}

		}
	}
	
	
	@Test
	void testAddPersonn() throws SQLException {
		var persons = List.of(
				new Person("Bradley Cooper", LocalDate.of(1975, 1, 5)),		
				new Person("Gene Hackman", LocalDate.of(1930, 1, 30)),			
				new Person("Morgan Freeman", LocalDate.of(1937, 6, 1))		
		);
		String sql = "insert into individu (prenom, nom, date_naissance) values (?,?,?)";
		try(
				Connection conn = ds.getConnection();
				PreparedStatement request = conn.prepareStatement(sql);
		) {
			for (var person: persons) {
				String fullname = person.getName();
				var parts = fullname.split(" ");
				System.out.println(Arrays.toString(parts));
				request.setString(1, parts[0]);
				request.setString(2, parts[1]);
				request.setDate(3, Date.valueOf(person.getBirthdate()) );
				request.executeUpdate();
			}
		}
	}
	
	
}	



