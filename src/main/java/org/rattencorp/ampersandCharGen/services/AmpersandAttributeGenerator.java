package org.rattencorp.ampersandCharGen.services;

import org.rattencorp.ampersandCharGen.core.attributes.AmpersandAttribute;
import org.rattencorp.ampersandCharGen.core.attributes.AmpersandAttributeAssignmentImpl;
import org.rattencorp.core.attributes.Attribute;
import org.rattencorp.core.attributes.AttributeValueGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Generate values and allow for the distribution of these values among the attributes by user input
 */
@Service
public class AmpersandAttributeGenerator {

    private final AttributeValueGenerator<AmpersandAttribute> attributeValueGenerator;

    private final AmpersandAttributeAssignmentImpl currentAssignment;

    private List<Integer> avalableValues;

    AmpersandAttributeGenerator(AttributeValueGenerator<AmpersandAttribute> attributeValueGenerator) {
        this.attributeValueGenerator = attributeValueGenerator;
        this.currentAssignment = new AmpersandAttributeAssignmentImpl();
    }


    public void generateAttributeValues(){
        avalableValues = Arrays.stream(attributeValueGenerator.generateValues()).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    String displayCurrentAssignment(){
        return currentAssignment.toString();
    }

    String displayRemainingChoises(){
        StringBuilder result = new StringBuilder();

        result.append("remaining unassigned values: ");

        for (Integer avalableValue : avalableValues) {
            result.append(avalableValue).append(",%n");
        }

        result.append(" remaining unassigned attributes: %n");

        for (Attribute a : AmpersandAttribute.values()) {
            if (currentAssignment.getValueForAttribute(a) == 0){
                result.append(a.toString()).append(",%n");
            }
        }

        result.deleteCharAt(result.length() - 2); //delete last comma

        return result.toString();
    }


    public AmpersandAttributeAssignmentImpl generateAttributeAssignment() {
        return new AmpersandAttributeAssignmentImpl();
    }




    public void assignValueToAttribute(int value, AmpersandAttribute attribute) {
        if (avalableValues.contains(value)) {
            if (currentAssignment.getValueForAttribute(attribute) != 0) {
                avalableValues.add(currentAssignment.getValueForAttribute(attribute));
                currentAssignment.assignAttribute(attribute, 0);
            }
            currentAssignment.assignAttribute(attribute, value);
            avalableValues.remove(value);
        }else{
            throw new IllegalArgumentException("Value %d is not available in: %s".formatted(value, avalableValues));
        }

    }

}
