package com.reactive.reactivespring.playground;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxMonoErrorTest {

    @Test
    public void onResumeTest(){

        Flux<String> flux = Flux.just("S","D")
                .concatWith(Flux.just("Df"))
                .concatWith(Flux.error(new RuntimeException("Pgat gya")))
                .onErrorResume((e)->{
                    System.out.println("Error is "+e);
                    return Flux.just("default");
                });
                ;
        StepVerifier.create(flux.log())
        .expectSubscription()
        .expectNext("S","D","Df","default")
        .verifyComplete();

    }


    @Test
    public void onReturnTest(){

        Flux<String> flux = Flux.just("S","D")
                .concatWith(Flux.just("Df"))
                .concatWith(Flux.error(new RuntimeException("Pgat gya")))
                .onErrorReturn("default");
        ;
        StepVerifier.create(flux.log())
                .expectSubscription()
                .expectNext("S","D","Df","default")
                .verifyComplete();

    }

    @Test
    public void onMapReturnTest(){

        Flux<String> flux = Flux.just("S","D")
                .concatWith(Flux.just("Df"))
                .concatWith(Flux.error(new RuntimeException("Pgat gya")))
                .onErrorMap((e)->{
                    return new CustomError(e);
                });
        ;
        StepVerifier.create(flux.log())
                .expectSubscription()
                .expectNext("S","D","Df")
                .expectError(CustomError.class)
                .verify();

    }
}
