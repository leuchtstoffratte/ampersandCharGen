package org.rattencorp.ampersandCharGen.core.attributes.valuegens;


import org.rattencorp.ampersandCharGen.core.attributes.AmpersandAttribute;
import org.rattencorp.core.attributes.AttributeValueGenerator;

import java.util.Random;

public class BasicRandomAmpersandAttributesValueGeneratorImpl implements AttributeValueGenerator<AmpersandAttribute> {


    private static final Random RANDOM = new Random();



    @Override
    public int[] generateValues() {
        var result = new int[6];

        for (int i : result) {
            result[i] = generateSingleValue();
        }

        return result;
    }


    /**
     * roll 4 d6, discard the lowest and
     * @return
     */
    private int generateSingleValue(){
        int min = d6Result();

        int result = 0;

        for (int i = 0; i<3; i++){
            int next = d6Result();

            result += Math.max(min, next);
            min = Math.min(min, next);
        }

        return result;
    }


    private int d6Result(){
        return RANDOM.nextInt(1,7);
    }

}
