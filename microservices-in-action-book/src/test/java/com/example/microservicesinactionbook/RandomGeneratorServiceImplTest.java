package com.example.microservicesinactionbook;

import com.example.microservicesinactionbook.service.RandomGeneratorService;
import com.example.microservicesinactionbook.service.RandomGeneratorServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class RandomGeneratorServiceImplTest {
    private RandomGeneratorService randomGeneratorService;

    @Before
    public void setUp(){
        randomGeneratorService = new RandomGeneratorServiceImpl();
    }

    @Test
    public void generateRandomFactorIsBetweenExpectedLimits() throws Exception{
        // when a good sample of factors generated
        List<Integer> randomFactors = IntStream.range(0, 1000)
                .map(i -> randomGeneratorService.generateRandomFactor())
                .boxed().collect(Collectors.toList());

        //then all of them should be between 11 and 100, - middle-complexity calculation
        assertThat(randomFactors).containsOnlyElementsOf(IntStream.range(11, 100).boxed().collect(Collectors.toList()));
    }
}
