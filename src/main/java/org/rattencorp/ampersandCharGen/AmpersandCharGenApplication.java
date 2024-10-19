package org.rattencorp.ampersandCharGen;

import org.rattencorp.belanglos.FristConfigClass;
import org.rattencorp.core.CoreSpringConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AmpersandCharGenApplication {

	public static void main(String[] args) {

		var context = new SpringApplicationBuilder(AmpersandCharGenApplication.class, CoreSpringConfig.class)
			.run(args);

		for (String s : context.getBeanDefinitionNames())
			System.out.println(s);

	}

}
