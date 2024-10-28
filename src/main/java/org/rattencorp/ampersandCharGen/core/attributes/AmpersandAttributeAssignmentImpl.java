package org.rattencorp.ampersandCharGen.core.attributes;

import org.rattencorp.core.attributes.Attribute;
import org.rattencorp.core.attributes.AttributeAssignement;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;


/**
 * Container class for the attribute values of a character
 */
public class AmpersandAttributeAssignmentImpl implements AttributeAssignement<AmpersandAttribute> {

    private final EnumMap<AmpersandAttribute, Integer> attributes;

    private final List<Integer> availableValues = new ArrayList<>();


    public AmpersandAttributeAssignmentImpl(){
        attributes = new EnumMap<>(AmpersandAttribute.class);
    }


    public List<Integer> getAvailableValues() {
        return availableValues;
    }


    @Override
    public int getValueForAttribute(AmpersandAttribute attribute) {
        return attributes.getOrDefault(attribute,0);
    }

    @Override
    public void assignAttribute(AmpersandAttribute attribute, int value) {
        attributes.put(attribute,value);
    }

    @Override
    public void wipeAssignments() {

        for (AmpersandAttribute attribute : attributes.keySet()) {
            assignAttribute(attribute, 0);
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


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (AmpersandAttribute attribute : AmpersandAttribute.values()) {
            builder.append(attribute).append("=").append(getValueForAttribute(attribute)).append(", ");
        }

        if (!builder.isEmpty()) //delete last trailing comma
            builder.delete(builder.length()-2, builder.length());

        return builder.toString();
    }

}
