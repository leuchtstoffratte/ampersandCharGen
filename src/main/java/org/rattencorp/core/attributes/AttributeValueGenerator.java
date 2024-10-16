package org.rattencorp.core.attributes;

public interface AttributeValueGenerator <A extends Attribute> {

    int[] generateValues();

}
