package org.rattencorp.ampersand.ampersandCharGen.character;

import org.rattencorp.ampersand.core.attributes.AmpersandAttribute;
import org.rattencorp.ampersandlike.classes.AmpersandClass;
import org.rattencorp.core.attributes.AttributeAssignment;

import java.util.Stack;

public final class AmpersandCharacter {

    private final AttributeAssignment<AmpersandAttribute> attributeAssignment;
    private final Stack<AmpersandClass> classes;

    public AmpersandCharacter(AttributeAssignment<AmpersandAttribute> attributeAssignment) {
        this.attributeAssignment = attributeAssignment;
        classes = new Stack<>();
    }


    public Stack<AmpersandClass> getClasses() {
        return classes;
    }

    public boolean hasClass(Class<? extends AmpersandClass> c){
        return classes.stream().anyMatch(a -> a.getClass().equals(c));
    }

    public int getLevel(){
        return classes.size();
    }

    public long getClassLevel(Class<? extends AmpersandClass> classForLevel){
        if (classes.isEmpty()){
            return 0;
        }

        return classes.stream().filter(c -> c.getClass().isAssignableFrom(classForLevel) ||
                classForLevel.isAssignableFrom(c.getClass())).count();
    }

    public boolean isMultiClass(){
        return classes.size() != getClassLevel(classes.getFirst().getClass());
    }

    public AttributeAssignment<AmpersandAttribute> getAttributeAssignment() {
        return attributeAssignment;
    }
}
