package org.rattencorp.ampersand.ampersandCharGen.services;


import org.rattencorp.ampersand.ampersandCharGen.character.AmpersandCharacter;
import org.rattencorp.ampersand.core.ampersandClasses.classProviders.AmpersandClassProvider;
import org.rattencorp.ampersandlike.classes.AmpersandClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AmpersandClassAssigner {


    private final List<AmpersandClassProvider> classProviderList;

    private final ConfigurableApplicationContext applicationContext;

    @Autowired
    public AmpersandClassAssigner(List<AmpersandClassProvider> classProviderList, ConfigurableApplicationContext context){
        this.classProviderList = classProviderList;
        this.applicationContext = context;
    }

    public void assignClassToCharacter(AmpersandCharacter myChar, Class<? extends AmpersandClass> newLevel) {

        var nextLevelBean = applicationContext.getBean(newLevel);

        if ( nextLevelBean.prerequisiteSupplier().test(myChar)){
            myChar.getClasses().push(nextLevelBean);
        }

    }

    public Set<AmpersandClass> getAllClasses() {

        Set<AmpersandClass> classes = new HashSet<>();

        for (AmpersandClassProvider classProvider : classProviderList) {
            for (Class<? extends AmpersandClass> aClass : classProvider.getAmpersandClasses()){
                classes.add(applicationContext.getBean(aClass));
            }
        }

        return classes;

    }


    public Set<AmpersandClass> getAllOptionsForChar(AmpersandCharacter myChar) {
        return getAllClasses().stream().filter(c -> c.prerequisiteSupplier().test(myChar)).collect(Collectors.toSet());
    }

}
