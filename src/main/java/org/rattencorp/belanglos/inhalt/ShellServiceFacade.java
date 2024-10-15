package org.rattencorp.belanglos.inhalt;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellServiceFacade {


    private final SomeService someService;


    ShellServiceFacade (SomeService someService) {
        this.someService = someService;
    }


    @ShellMethod
    public String greet(){
        return someService.sayHello();

    }


}
