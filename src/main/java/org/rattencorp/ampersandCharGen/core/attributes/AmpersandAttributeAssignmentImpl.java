package org.rattencorp.ampersandCharGen.core.attributes;

import org.rattencorp.core.attributes.Attribute;
import org.rattencorp.core.attributes.AttributeAssignement;
import java.util.EnumMap;


/**
 * Container class for the attribute values of a character
 */
public class AmpersandAttributeAssignmentImpl implements AttributeAssignement {

    private final EnumMap<AmpersandAttribute, Integer> attributes;

    public AmpersandAttributeAssignmentImpl(){
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

    @Override
    public void assignAttribute(Attribute attribute, int value) {
        if (attribute instanceof AmpersandAttribute a ){
            attributes.put(a,value);
        }else{
            throw new IllegalArgumentException("Unknown attribute " + attribute + ". No value was assigned.");
        }
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        else if ((obj == null) || getClass() != obj.getClass())
            return false;
        else
            return ((AmpersandAttributeAssignmentImpl) obj).attributes.equals(attributes);
    }

    @Override
    public int hashCode() {
        return attributes.hashCode();
    }

}
