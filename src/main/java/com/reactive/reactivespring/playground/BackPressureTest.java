package com.reactive.reactivespring.playground;

import jdk.nashorn.internal.parser.JSONParser;
import org.junit.Test;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class BackPressureTest {

    @Test
    public void backPreTest(){

        Flux<Integer> stringFlux = Flux.range(1,10)
                .take(2);

        StepVerifier.create(stringFlux.log())
                .expectSubscription()
                .expectNext(1,2)
                .verifyComplete();

    }

    @Test
    public void check(){

        Flux<Integer> stringFlux = Flux.range(1,10);

        stringFlux.subscribe((string)-> System.out.println(string),(e)->System.out.println("Error is "+e)
                ,()->System.out.println("done")
        ,subscription -> subscription.request(2));


    }

    @Test
    public void customBack(){

        Flux<Integer> stringFlux = Flux.range(1,10);

        stringFlux.subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnNext(Integer value) {
                System.out.println(value);
                if (value==4){
                    cancel();
                }
            }
        });
    }
}
