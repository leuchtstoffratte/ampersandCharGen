package org.rattencorp.ampersand.ampersandCharGen.character;

import org.rattencorp.ampersand.ampersandCharGen.attributes.valuegens.AmpersandAttributes;
import org.rattencorp.ampersandlike.classes.AmpersandClass;

import java.util.Stack;

public final class AmpersandCharacter {

    private AmpersandAttributes attributes;
    private final Stack<AmpersandClass> classes;

    public AmpersandCharacter(AmpersandAttributes attributeAssignment) {
        this.attributes = attributeAssignment;
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

        return classes.stream()
                .filter(c -> c.getClass().isAssignableFrom(classForLevel)
                                || classForLevel.isAssignableFrom(c.getClass()))
                .count();
    }

    public boolean isMultiClass(){
        return classes.size() != getClassLevel(classes.getFirst().getClass());
    }

    public AmpersandAttributes getAttributes() {
        return attributes;
    }
    public void setAttributes(AmpersandAttributes attributes) {this.attributes = attributes;}
}
