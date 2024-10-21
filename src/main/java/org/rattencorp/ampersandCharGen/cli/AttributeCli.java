package org.rattencorp.ampersandCharGen.cli;

import org.rattencorp.ampersandCharGen.core.attributes.AmpersandAttribute;
import org.rattencorp.ampersandCharGen.services.AmpersandAttributeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class AttributeCli {

    private final AmpersandAttributeGenerator ampersandAttributeGenerator;

    @Autowired
    AttributeCli(AmpersandAttributeGenerator ampersandAttributeGenerator) {
        this.ampersandAttributeGenerator = ampersandAttributeGenerator;
    }


    @ShellMethod("generate a new set of values to choose assginments from")
    public String generateAttributes() {
        ampersandAttributeGenerator.generateAttributeValues();

        return ampersandAttributeGenerator.getAvailableValues().toString();
    }


    @ShellMethod("display which attribute has currently which value")
    public String displayCurrentAssignment(){
        return ampersandAttributeGenerator.getCurrentAssignment().toString();
    }

    @ShellMethod("displays the as yet not assigned values and attributes")
    public String displayRemainingChoices(){
        StringBuilder result = new StringBuilder();

        result.append("remaining unassigned values: ");

        for (Integer availableValue : ampersandAttributeGenerator.getAvailableValues()) {
            result.append(availableValue).append(",%n");
        }

        result.append(" remaining unassigned attributes: %n");

        for (AmpersandAttribute a : AmpersandAttribute.values()) {
            if (ampersandAttributeGenerator.getCurrentAssignment().getValueForAttribute(a) == 0){
                result.append(a.toString()).append(",%n");
            }
        }

        result.deleteCharAt(result.length() - 2); //delete last comma

        return result.toString();
    }


    @ShellMethod("assign-STR")
    public String assignSTR(String shouldBeInt) {
        return assignAttribute(shouldBeInt, AmpersandAttribute.STR);
    }


    public String assignAttribute(String shouldBeInt, AmpersandAttribute attribute) {

        try{
            int input = Integer.parseInt(shouldBeInt);

            this.ampersandAttributeGenerator.assignValueToAttribute(input, attribute);
            return "successfully assigned value '%d' to attribute '%s'. %n new distribution is %s "
                    .formatted(input, attribute.name(), displayCurrentAssignment());


        } catch (Exception e){
            return "'%s' needs to be an integer from the listed options: %s%n".formatted(shouldBeInt, displayRemainingChoices());
        }

    }


}