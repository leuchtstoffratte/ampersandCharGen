package org.rattencorp.ampersand.core.ampersandClasses.coreClasses;

import org.rattencorp.ampersand.core.ampersandClasses.classProviders.AmpersandClassProvider;
import org.rattencorp.ampersandlike.classes.AmpersandClass;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CoreBookClassProvider implements AmpersandClassProvider {


    @Override
    public Set<Class<? extends AmpersandClass>> getAmpersandClasses() {
        return Set.of(Fighter.class, Rogue.class, Wizard.class);
    }
}
