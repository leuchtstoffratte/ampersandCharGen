package org.rattencorp.ampersandCharGen.core.attributes;

import org.rattencorp.core.attributes.Attribute;
import org.rattencorp.core.attributes.AttributeAssignement;
import java.util.EnumMap;


public class AttributeAssignmentImpl implements AttributeAssignement {

    private final EnumMap<AmpersandAttribute, Integer> attributes;

    public AttributeAssignmentImpl(){
        attributes = new EnumMap<>(AmpersandAttribute.class);
    }


    @Override
    public int getValueForAttribute(Attribute attribute) {
        if (attribute instanceof AmpersandAttribute a ){
            return attributes.getOrDefault(a,0);
        }else{
            throw new IllegalArgumentException("Attribute " + attribute + " is not assigned.");
        }
    }
}
