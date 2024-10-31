package org.rattencorp.ampersand.ampersandCharGen.cli;

import jakarta.annotation.Resource;
import org.rattencorp.ampersand.core.attributes.AmpersandAttribute;
import org.rattencorp.ampersand.ampersandCharGen.services.AmpersandAttributeGenerator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class AttributeCli {

    @Resource(name ="attributeGeneration")
    private AmpersandAttributeGenerator ampersandAttributeGenerator;



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
    public String assignStr(String shouldBeInt) {
        return assignAttribute(shouldBeInt, AmpersandAttribute.STR);
    }

    @ShellMethod("assign-DEX")
    public String assignDex(String shouldBeInt) {
        return assignAttribute(shouldBeInt, AmpersandAttribute.DEX);
    }

    @ShellMethod("assign-CON")
    public String assignCon(String shouldBeInt) {
        return assignAttribute(shouldBeInt, AmpersandAttribute.CON);
    }

    @ShellMethod("assign-INT")
    public String assignInt(String shouldBeInt) {
        return assignAttribute(shouldBeInt, AmpersandAttribute.INT);
    }

    @ShellMethod("assign-WIS")
    public String assignWis(String shouldBeInt) {
        return assignAttribute(shouldBeInt, AmpersandAttribute.WIS);
    }

    @ShellMethod("assign-CHAR")
    public String assignChar(String shouldBeInt) {
        return assignAttribute(shouldBeInt, AmpersandAttribute.CHAR);
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
