package org.rattencorp.ampersand.ampersandCharGen.attributes.valuegens;

import org.rattencorp.ampersand.core.attributes.AmpersandAttribute;
import org.rattencorp.core.attributes.AttributeValueGenerator;

//@Component
public class DefaultAmpersantAttributeValueGeneratorImpl implements AttributeValueGenerator<AmpersandAttribute> {


    @Override
    public int[] generateValues() {
        return new int[]{15,14,13,12,10,8};
    }
}
