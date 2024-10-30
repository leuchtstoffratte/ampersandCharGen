package org.rattencorp.core.attributes;

import java.io.Serializable;

public interface AttributeAssignment<A extends Attribute> extends Serializable {


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
