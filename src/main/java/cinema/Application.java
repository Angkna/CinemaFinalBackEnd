package cinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication			//=c'est une applic que springboot gère !
public class Application {

		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);  // c'est spring boot qui gère tout
		}
}
//scan toutes les classes sous cinema donc donne le point d'entrée de tout les autres parametres