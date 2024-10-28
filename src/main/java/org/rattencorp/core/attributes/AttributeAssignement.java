package org.rattencorp.core.attributes;

public interface AttributeAssignement <A extends Attribute> {


    int getValueForAttribute(A attribute);

    /**
     * establish assigment between attribute and value
     *
     * @param attribute
     * @param value
     */
    void assignAttribute(A attribute, int value);


    /**
     * remove all current assignments of values to attributes
     */
    void wipeAssignments();

}
