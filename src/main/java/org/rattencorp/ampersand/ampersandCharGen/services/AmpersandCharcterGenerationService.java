package org.rattencorp.ampersand.ampersandCharGen.services;

import org.rattencorp.ampersand.ampersandCharGen.character.AmpersandCharacter;
import org.rattencorp.ampersandlike.classes.AmpersandClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * service to bundle the entire character generation process
 *
 *
 *
 */
@Service
public class AmpersandCharcterGenerationService {

    private final AmpersandAttributeGenerator ampersandAttributeGenerator;
    private final AmpersandClassAssigner ampersandClassAssigner;


    @Autowired
    public AmpersandCharcterGenerationService( AmpersandAttributeGenerator ampersandAttributeGenerator, AmpersandClassAssigner ampersandClassAssigner) {
        this.ampersandAttributeGenerator = ampersandAttributeGenerator;
        this.ampersandClassAssigner = ampersandClassAssigner;
    }


    /*
     *  TODO:
     *   - create a character with attributes and class levels
     *   - export as JSON
     *   - import from JSON
     *
     *   TODO eventually:
     *    - persistence via JPA
     *    - integate equipment
     *    - integrate origins/races/heritages/species/backgrounds etc
     *
     *   TODO maybe:
     *    - integrate skill selection
     *
     *
     */



    public AmpersandCharacter createCharacter(Class<? extends AmpersandClass> firstClass ) {

        ampersandAttributeGenerator.generateAttributeValues();

        AmpersandCharacter ampersandCharacter = new AmpersandCharacter(ampersandAttributeGenerator.getCurrentAssignment());

        ampersandClassAssigner.assignClassToCharacter(ampersandCharacter, firstClass);

        return ampersandCharacter;
    }




}
