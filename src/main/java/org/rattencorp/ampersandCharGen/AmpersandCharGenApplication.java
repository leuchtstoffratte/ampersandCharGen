package org.rattencorp.ampersandCharGen;


import org.rattencorp.ampersandCharGen.core.attributes.valuegens.AmpersandAttributeValueGeneratorConfig;
import org.rattencorp.core.CoreSpringConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AmpersandCharGenApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder(AmpersandCharGenApplication.class, CoreSpringConfig.class, AmpersandAttributeValueGeneratorConfig.class)
			.run(args);

	}

}
