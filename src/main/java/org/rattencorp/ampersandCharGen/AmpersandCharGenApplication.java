package org.rattencorp.ampersandCharGen;

import org.rattencorp.belanglos.FristConfigClass;
import org.rattencorp.core.CoreSpringConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AmpersandCharGenApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder(AmpersandCharGenApplication.class, FristConfigClass.class, CoreSpringConfig.class)
			.run(args);

	}

}
