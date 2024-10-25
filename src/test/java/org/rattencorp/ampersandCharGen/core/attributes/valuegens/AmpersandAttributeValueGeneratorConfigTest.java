package org.rattencorp.ampersandCharGen.core.attributes.valuegens;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AmpersandAttributeValueGeneratorConfigTest {

    @Mock
    private ApplicationContext context;

    @Mock
    private Environment environment;

    private final AmpersandAttributeValueGeneratorConfig testee = new AmpersandAttributeValueGeneratorConfig();


    @BeforeEach
    void setUp() {

        Mockito.when(context.getEnvironment()).thenReturn(environment);
    }

    @Test
    void ampersandAttributeGeneratorShouldProvideDefaultWhenNoArgsAreGiven() {

        //GIVEN
        Mockito.doReturn("").when(environment).getProperty(Mockito.anyString(), Mockito.anyString());

        //WHEN
        var result = testee.ampersandAttributeGenerator(context);

        //THEN
        Assertions.assertThat(result).isInstanceOf(DefaultAmpersantAttributeValueGeneratorImpl.class);

    }


    @ParameterizedTest
    @ValueSource(strings = {AmpersandAttributeValueGeneratorConfig.BASIC_RANDOM, AmpersandAttributeValueGeneratorConfig.THREE_D6})
    void ampersandAttributeGeneratorShouldProvideSimpleRandomWhenArgsContainGivenName(String name) {

        //GIVEN
        Mockito.doReturn(name).when(environment).getProperty(Mockito.anyString(), Mockito.anyString());

        //WHEN
        var result = testee.ampersandAttributeGenerator(context);

        //THEN
        Assertions.assertThat(result).isInstanceOf(BasicRandomAmpersandAttributesValueGeneratorImpl.class);

    }


}