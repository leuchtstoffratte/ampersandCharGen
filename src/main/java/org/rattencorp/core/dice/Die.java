package org.rattencorp.core.dice;

public sealed interface Die permits D4, D6, D8, D10, D12, D20  {

    int roll();

}
