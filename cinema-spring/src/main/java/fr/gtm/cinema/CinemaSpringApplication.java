package fr.gtm.cinema;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAsync
//ajout des bibliothèques psrinfox swagger 2 et ui
@EnableSwagger2
public class CinemaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaSpringApplication.class, args);
	}
	
	@Bean
	public Docket api() {
		//http://localhost:7070/swagger-ui.html pour la doc
		return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.basePackage("fr.gtm.cinema.rest"))
					.build()
					.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("REST API de gestion de films")
				.contact(new Contact("Commercial", "http://bovoyages.net", "noreply@bovoyages.net"))
				.version("0.alpha")
				.build();
	}

}
