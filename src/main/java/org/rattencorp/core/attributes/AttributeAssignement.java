package org.rattencorp.core.attributes;

public interface AttributeAssignement <A extends Attribute> {

    int getValueForAttribute(A attribute);

    void assignAttribute(A attribute, int value);


}
