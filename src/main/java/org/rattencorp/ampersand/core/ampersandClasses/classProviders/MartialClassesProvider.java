package org.rattencorp.ampersand.core.ampersandClasses.classProviders;

import org.rattencorp.ampersand.core.ampersandClasses.coreClasses.Fighter;
import org.rattencorp.ampersand.core.ampersandClasses.coreClasses.Rogue;
import org.rattencorp.ampersandlike.classes.AmpersandClass;
import org.rattencorp.ampersandlike.classes.MartialClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@Profile("allowAllBooks")
public class MartialClassesProvider implements AmpersandClassProvider {

    @Autowired
    private Set<AmpersandClass> ampersandClasses;

    @Override
    public Set<Class<? extends AmpersandClass>> getAmpersandClasses() {
        return ampersandClasses.stream().map(AmpersandClass::getClass).filter(c -> c.isAnnotationPresent(MartialClass.class)).collect(Collectors.toSet());
    }
}
