package org.rattencorp.ampersand.core.ampersandClasses.classProviders;

import org.rattencorp.ampersand.core.ampersandClasses.coreClasses.Wizard;
import org.rattencorp.ampersandlike.classes.AmpersandClass;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Profile("allowAllBooks")
public class CasterClassesProvider implements AmpersandClassProvider {

    @Override
    public Set<Class<? extends AmpersandClass>> getAmpersandClasses() {
        return Set.of(Wizard.class);
    }
}
