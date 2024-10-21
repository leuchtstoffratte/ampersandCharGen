package org.rattencorp.ampersandCharGen.core.attributes.valuegens;

import org.rattencorp.ampersandCharGen.core.attributes.AmpersandAttribute;
import org.rattencorp.core.attributes.AttributeValueGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmpersandAttributeValueGeneratorConfig {

    /**
     * Option for swapping out value generation
     */
    private static final String ATTRIBUTE_GENERATOR_TYPE = "attribGen";


    static final String BASIC_RANDOM = "random";
    static final String THREE_D6 = "3D6";

    @Bean
    public AttributeValueGenerator<AmpersandAttribute> ampersandAttributeGenerator(@Autowired ApplicationArguments arguments) {

        if (arguments.getOptionValues(ATTRIBUTE_GENERATOR_TYPE) == null || arguments.getOptionValues(ATTRIBUTE_GENERATOR_TYPE).isEmpty())
            return new DefaultAmpersantAttributeValueGeneratorImpl();

        return switch (arguments.getOptionValues(ATTRIBUTE_GENERATOR_TYPE).getFirst()){
            case THREE_D6, BASIC_RANDOM -> new BasicRandomAmpersandAttributesValueGeneratorImpl();

            default -> new DefaultAmpersantAttributeValueGeneratorImpl();

        };

    }
}
