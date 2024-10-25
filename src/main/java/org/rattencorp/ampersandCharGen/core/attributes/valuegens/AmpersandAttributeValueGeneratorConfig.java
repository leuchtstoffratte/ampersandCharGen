package org.rattencorp.ampersandCharGen.core.attributes.valuegens;

import org.rattencorp.ampersandCharGen.core.attributes.AmpersandAttribute;
import org.rattencorp.core.attributes.AttributeValueGenerator;
import org.rattencorp.core.dice.D6;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AmpersandAttributeValueGeneratorConfig {

    /**
     * Option for swapping out value generation
     */
    private static final String ATTRIBUTE_GENERATOR_TYPE = "attribute.generatorType";

    static final String BASIC_RANDOM = "random";
    static final String THREE_D6 = "3D6";

    @Bean
    public AttributeValueGenerator<AmpersandAttribute> ampersandAttributeGenerator(@Autowired ApplicationContext context) {

        var attribGeneratorType = context.getEnvironment().getProperty(ATTRIBUTE_GENERATOR_TYPE, "None");

        return switch (attribGeneratorType){
            case THREE_D6, BASIC_RANDOM -> new BasicRandomAmpersandAttributesValueGeneratorImpl(new D6());

            default -> new DefaultAmpersantAttributeValueGeneratorImpl();

        };

    }
}
