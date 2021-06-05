package com.reactive.reactivespring.playground;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxMonoTest {


    @Test
    public void fluxNotError(){

        Flux<String> stringFlux = Flux.just("Shashawat","Shuchi","Aditi").log();

        stringFlux.subscribe(System.out::println, (e)->{System.out.println("Exception is"+e);},
                ()->System.out.println("Completed"));

        StepVerifier.create(stringFlux)
                .expectNext("Shashawat")
                .expectNext("Shuchi")
                .expectNext("Aditi")
                .verifyComplete();
    }

    @Test
    public void monoTest(){

        Mono<String> stringMono = Mono.just("ADCV");

        StepVerifier.create(stringMono.log())
                .expectNext("ADCV")
                .verifyComplete();
    }

    @Test
    public void monoErrorTest(){

        StepVerifier.create(Mono.error(new RuntimeException("Ho gya kand")))
        .expectError(RuntimeException.class)
        .verify();
    }
}
