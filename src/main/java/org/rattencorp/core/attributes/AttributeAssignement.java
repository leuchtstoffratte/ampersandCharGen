package org.rattencorp.core.attributes;

public interface AttributeAssignement <A extends Attribute> {

    int getValueForAttribute(A attribute);

}
