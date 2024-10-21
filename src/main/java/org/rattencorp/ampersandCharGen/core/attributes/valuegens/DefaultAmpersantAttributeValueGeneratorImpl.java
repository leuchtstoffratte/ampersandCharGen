package org.rattencorp.ampersandCharGen.core.attributes.valuegens;

import org.rattencorp.ampersandCharGen.core.attributes.AmpersandAttribute;
import org.rattencorp.core.attributes.AttributeValueGenerator;
import org.springframework.stereotype.Component;

//@Component
public class DefaultAmpersantAttributeValueGeneratorImpl implements AttributeValueGenerator<AmpersandAttribute> {


    @Override
    public int[] generateValues() {
        return new int[]{15,14,13,12,10,8};
    }
}
