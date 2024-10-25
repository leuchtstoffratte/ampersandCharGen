package org.rattencorp.core.dice;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public final class D10 implements Die{

    private final Random random = new Random();

    @Override
    public int roll() {
        return random.nextInt(1,11);
    }
}
