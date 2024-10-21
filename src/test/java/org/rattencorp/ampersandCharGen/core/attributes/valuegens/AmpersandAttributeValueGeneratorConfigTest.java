package org.rattencorp.ampersandCharGen.core.attributes.valuegens;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.ApplicationArguments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AmpersandAttributeValueGeneratorConfigTest {

    @Mock
    private ApplicationArguments arguments;

    private AmpersandAttributeValueGeneratorConfig testee = new AmpersandAttributeValueGeneratorConfig();;

    @Test
    void ampersandAttributeGeneratorShouldProvideDefaultWhenNoArgsAreGiven() {

        //GIVEN
        Mockito.doReturn(null).when(arguments).getOptionValues(Mockito.anyString());

        //WHEN
        var result = testee.ampersandAttributeGenerator(arguments);

        //THEN
        Assertions.assertThat(result).isInstanceOf(DefaultAmpersantAttributeValueGeneratorImpl.class);

    }

    @Test
    void ampersandAttributeGeneratorShouldProvideDefaultWhenEmptyArgsAreGiven() {

        //GIVEN
        Mockito.doReturn(Collections.emptyList()).when(arguments).getOptionValues(Mockito.anyString());

        //WHEN
        var result = testee.ampersandAttributeGenerator(arguments);

        //THEN
        Assertions.assertThat(result).isInstanceOf(DefaultAmpersantAttributeValueGeneratorImpl.class);

    }

    @ParameterizedTest
    @ValueSource(strings = {AmpersandAttributeValueGeneratorConfig.BASIC_RANDOM, AmpersandAttributeValueGeneratorConfig.THREE_D6})
    void ampersandAttributeGeneratorShouldProvideSimpleRandomWhenArgsContainGivenName(String name) {

        //GIVEN
        Mockito.doReturn(List.of(name)).when(arguments).getOptionValues(Mockito.anyString());

        //WHEN
        var result = testee.ampersandAttributeGenerator(arguments);

        //THEN
        Assertions.assertThat(result).isInstanceOf(BasicRandomAmpersandAttributesValueGeneratorImpl.class);

    }


}