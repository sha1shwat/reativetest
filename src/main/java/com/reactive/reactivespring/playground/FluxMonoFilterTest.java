package com.reactive.reactivespring.playground;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

public class FluxMonoFilterTest {

    List<String>  namesList = Arrays.asList("shubham","shashwat","vaibhav");

    @Test
    public void filterTest(){

         Flux<String> stringFlux = Flux.fromIterable(namesList)
                .filter(s->s.startsWith("v"))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("vaibhav")
                .verifyComplete();

    }

    @Test
    public void filterTestLength(){

        Flux<String> stringFlux = Flux.fromIterable(namesList)
                .filter(s->s.length() > 7)
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("shashwat")
                .verifyComplete();

    }

    @Test
    public void filterMapTest(){

        Flux<Integer> integerFlux = Flux.fromIterable(namesList)
                .filter(s->s.length()>7)
                .map(s->s.length())
                .repeat(1)
                .log();

        StepVerifier.create(integerFlux)
                .expectNext(8,8)
                .verifyComplete();
    }


}
