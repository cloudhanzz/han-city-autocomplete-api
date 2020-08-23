package han.jiayun.city.autocomplete.config;

import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author Jiayun Han
 *
 */
@Configuration
public class ProjectConfig {
	
	@Bean
	public JaroWinklerDistance jaroWinklerDistance() {
		return new JaroWinklerDistance();
	}

}
