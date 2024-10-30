package org.rattencorp.ampersand.core.ampersandClasses.classProviders;

import org.rattencorp.ampersandlike.classes.AmpersandClass;
import java.util.Set;

public interface AmpersandClassProvider {

    public Set<Class<? extends AmpersandClass>> getAmpersandClasses();

}
