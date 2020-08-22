package han.jiayun.city.autocomplete.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 
 * @author Jiayun Han
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
		
	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("han.jiayun.city.autocomplete.controllers"))
				.paths(PathSelectors.any())
				.build()
				.protocols(new HashSet<>(Arrays.asList("HTTP")));
	}

	private ApiInfo getApiInfo() {
		
		return new ApiInfoBuilder()
		           .title("City Autocomplete API")
		           .description("APIs for providing auto-complete suggestions based on input")
		           .version("1.0")
		           .contact(new Contact("Jiayun Han", 
		   				                "https://github.com/cloudhanzz/han-city-autocomplete-api/tree/master", 
		   				                "jiayunhan@gmail.com"))
		           .build();
	}

}
