package org.rattencorp.AmpersandCharGen;

import org.rattencorp.belanglos.FristConfigClass;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AmpersandCharGenApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder(AmpersandCharGenApplication.class, FristConfigClass.class)
			.run(args);

	}

}
