package com.example.Service3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Service3Application {

	public static void main(String[] args) {
		SpringApplication.run(Service3Application.class, args);
	}

}


@RestController // Indique que cette classe est un contrôleur Spring MVC avec des capacités RESTful
class AggregatorController { // Contrôleur pour gérer les requêtes entrantes et agréger les données

	@Autowired // Injecte le bean GreetingClient
	private GreetingClient greetingClient; // Client Feign pour appeler le Service A

	@Autowired // Injecte le bean DateClient
	private DateClient dateClient; // Client Feign pour appeler le Service B

	@GetMapping("/aggregate") // Mappe les requêtes HTTP GET vers cette méthode à l'endpoint "/aggregate"
	public String getAggregateData() { // Méthode pour agréger les données des deux services
		String greeting = greetingClient.getGreeting(); // Appelle le Service A pour obtenir le message de salutation
		String date = dateClient.getCurrentDate(); // Appelle le Service B pour obtenir la date actuelle
		return greeting + " | " + date; // Retourne la réponse agrégée
	}
}

@FeignClient(name = "Service1")
interface GreetingClient{
	@GetMapping("/greeting")
	String getGreeting();
}


@FeignClient(name = "Service2")
interface DateClient{
	@GetMapping("/date")
	String getCurrentDate();
}