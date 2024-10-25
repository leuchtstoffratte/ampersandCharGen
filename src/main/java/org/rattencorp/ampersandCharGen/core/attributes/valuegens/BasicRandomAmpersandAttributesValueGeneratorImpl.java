package org.rattencorp.ampersandCharGen.core.attributes.valuegens;


import org.rattencorp.ampersandCharGen.core.attributes.AmpersandAttribute;
import org.rattencorp.core.attributes.AttributeValueGenerator;
import org.rattencorp.core.dice.D6;
import org.springframework.beans.factory.annotation.Autowired;


public class BasicRandomAmpersandAttributesValueGeneratorImpl implements AttributeValueGenerator<AmpersandAttribute> {


    private final D6 d6;


    BasicRandomAmpersandAttributesValueGeneratorImpl (@Autowired D6 d6) {
        this.d6 = d6;
    }

    @Override
    public int[] generateValues() {
        var result = new int[6];

        for (int i = 0; i < 6; i++) {
            result[i] = generateSingleValue();
        }

        return result;
    }


    /**
     * roll 4 d6, discard the lowest and
     * return sum of remaining values
     */
    private int generateSingleValue(){
        int min = d6.roll();

        int result = 0;

        for (int i = 0; i<3; i++){
            int next = d6.roll();

            result += Math.max(min, next);
            min = Math.min(min, next);
        }

        return result;
    }


}
