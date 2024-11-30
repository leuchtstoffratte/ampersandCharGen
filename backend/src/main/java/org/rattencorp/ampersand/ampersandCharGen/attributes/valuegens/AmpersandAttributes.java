package org.rattencorp.ampersand.ampersandCharGen.attributes.valuegens;

import org.rattencorp.ampersand.core.attributes.AmpersandAttribute;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

public record AmpersandAttributes(int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {



    public static AmpersandAttributes parseJSON(String attributes) {
        //TODO
        return new AmpersandAttributes (0,0,0,0,0,0);
    }



    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class AmpersandAttributeBuilder {

        private final Map<AmpersandAttribute, Integer> attributes = new EnumMap<>(AmpersandAttribute.class);


        public AmpersandAttributeBuilder strength(int strength) {
            attributes.put(AmpersandAttribute.STR, strength);
            return this;
        }

        public AmpersandAttributeBuilder constitution(int constitution) {
            attributes.put(AmpersandAttribute.CON, constitution);
            return this;
        }

        public AmpersandAttributeBuilder dexterity(int dexterity) {
            attributes.put(AmpersandAttribute.DEX, dexterity);
            return this;
        }

        public AmpersandAttributeBuilder intelligence(int intelligence) {
            attributes.put(AmpersandAttribute.INT, intelligence);
            return this;
        }

        public AmpersandAttributeBuilder wisdom(int wisdom) {
            attributes.put(AmpersandAttribute.WIS, wisdom);
            return this;
        }

        public AmpersandAttributeBuilder charisma(int charisma) {
            attributes.put(AmpersandAttribute.CHAR, charisma);
            return this;
        }



        public AmpersandAttributes build(){

            return new AmpersandAttributes(
                    attributes.getOrDefault(AmpersandAttribute.STR, 0),
                    attributes.getOrDefault(AmpersandAttribute.DEX, 0),
                    attributes.getOrDefault(AmpersandAttribute.CON,0),
                    attributes.getOrDefault(AmpersandAttribute.INT, 0),
                    attributes.getOrDefault(AmpersandAttribute.WIS, 0),
                    attributes.getOrDefault(AmpersandAttribute.CHAR, 0)
            );


        }

    }

}
