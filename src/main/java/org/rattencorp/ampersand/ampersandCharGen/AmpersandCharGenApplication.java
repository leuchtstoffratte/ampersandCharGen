package org.rattencorp.ampersand.ampersandCharGen;


import org.rattencorp.ampersand.ampersandCharGen.attributes.valuegens.AmpersandAttributeValueGeneratorConfig;
import org.rattencorp.ampersand.core.AmpersandCoreConfig;
import org.rattencorp.core.CoreSpringConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AmpersandCharGenApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder(AmpersandCharGenApplication.class, CoreSpringConfig.class,
				AmpersandAttributeValueGeneratorConfig.class, AmpersandCoreConfig.class)
				.profiles("ampersand-char-gen", "random")
			.run(args);

	}

}
