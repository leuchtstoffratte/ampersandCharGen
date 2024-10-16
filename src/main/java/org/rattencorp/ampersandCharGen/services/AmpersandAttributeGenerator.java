package org.rattencorp.ampersandCharGen.services;

import org.rattencorp.ampersandCharGen.core.attributes.AttributeAssignmentImpl;
import org.rattencorp.core.attributes.AttributeValueGenerator;
import org.springframework.stereotype.Service;

@Service
public class AmpersandAttributeGenerator {

    private final AttributeValueGenerator attributeValueGenerator;


    AmpersandAttributeGenerator(AttributeValueGenerator attributeValueGenerator) {
        this.attributeValueGenerator = attributeValueGenerator;
    }


    String generateAttributeValues(){
        var display = new StringBuilder();


    }


    public AttributeAssignmentImpl generateAttributeAssignment() {


        return new AttributeAssignmentImpl();
    }

}
