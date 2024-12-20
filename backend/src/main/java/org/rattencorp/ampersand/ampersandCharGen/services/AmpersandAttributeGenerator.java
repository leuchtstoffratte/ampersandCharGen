package org.rattencorp.ampersand.ampersandCharGen.services;

import org.rattencorp.ampersand.core.attributes.AmpersandAttribute;
import org.rattencorp.ampersand.core.attributes.AmpersandAttributeAssignmentImpl;
import org.rattencorp.core.attributes.AttributeValueGenerator;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Generate values and allow for the distribution of these values among the attributes by user input
 */
@Service("attributeGeneration")
public class AmpersandAttributeGenerator {

    private final AttributeValueGenerator<AmpersandAttribute> attributeValueGenerator;

    private final AmpersandAttributeAssignmentImpl currentAssignment;


    @Autowired
    AmpersandAttributeGenerator(ObjectProvider<AttributeValueGenerator<AmpersandAttribute>> attributeValueGeneratorObjectProvider) {
        this.attributeValueGenerator = attributeValueGeneratorObjectProvider.getIfAvailable();
        this.currentAssignment = new AmpersandAttributeAssignmentImpl();
    }

    AmpersandAttributeGenerator(AttributeValueGenerator<AmpersandAttribute> attributeValueGenerator) {
        this.attributeValueGenerator = attributeValueGenerator;
        this.currentAssignment = new AmpersandAttributeAssignmentImpl();
    }


    public void generateAttributeValues(){
        currentAssignment.getAvailableValues().clear();
        currentAssignment.getAvailableValues()
                .addAll(
                        Arrays.stream(attributeValueGenerator.generateValues())
                                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll)
                );
        currentAssignment.wipeAssignments(); //otherwise the previous values might persist through already assigned values
    }

    public List<Integer> getAvailableValues() {
        return currentAssignment.getAvailableValues();
    }

    public AmpersandAttributeAssignmentImpl getCurrentAssignment() {
        return currentAssignment;
    }

    public void assignValueToAttribute(int value, AmpersandAttribute attribute) {
        if (currentAssignment.getAvailableValues().contains(value)) {
            if (currentAssignment.getValueForAttribute(attribute) != 0) {
                currentAssignment.getAvailableValues().add(currentAssignment.getValueForAttribute(attribute));
                currentAssignment.assignAttribute(attribute, 0);
            }
            currentAssignment.assignAttribute(attribute, value);
            currentAssignment.getAvailableValues().remove(Integer.valueOf(value));
        }else{
            throw new IllegalArgumentException("Value %d is not available in: %s".formatted(value, currentAssignment.getAvailableValues()));
        }

    }

}
