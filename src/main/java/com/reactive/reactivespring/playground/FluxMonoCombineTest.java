package com.reactive.reactivespring.playground;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class FluxMonoCombineTest {

    @Test
    public void combineFluxMerge(){

        Flux<String> flux1 = Flux.just("Q","W","E");
        Flux<String> flux2 = Flux.just("Z","X","C");

        Flux<String> mergeFlux = Flux.merge(flux1,flux2);

        StepVerifier.create(mergeFlux.log())
                .expectSubscription()
                .expectNext("Q","W","E","Z","X","C")
                .verifyComplete();
    }

    @Test
    public void combineFluxMergeWithDelay(){

        Flux<String> flux1 = Flux.just("A","S","D").delayElements(Duration.ofSeconds(1));
        Flux<String> flux2 = Flux.just("E","R","T").delayElements(Duration.ofSeconds(1));

        Flux<String> mergeFlux = Flux.merge(flux1,flux2);

        StepVerifier.create(mergeFlux.log())
                .expectSubscription()
                .expectNextCount(6)
                .verifyComplete();
    }

    @Test
    public void combineFluxMergeWithConcat(){

        Flux<String> flux1 = Flux.just("A","S","D").delayElements(Duration.ofSeconds(1));
        Flux<String> flux2 = Flux.just("E","R","T").delayElements(Duration.ofSeconds(1));

        Flux<String> mergeFlux = Flux.concat(flux1,flux2);

        StepVerifier.create(mergeFlux.log())
                .expectSubscription()
                .expectNext("A","S","D","E","R","T")
                .verifyComplete();

    }

    @Test
    public void combineFluxMergeWithZip(){

        Flux<String> flux1 = Flux.just("A","S","D").delayElements(Duration.ofSeconds(1));
        Flux<String> flux2 = Flux.just("E","R","T").delayElements(Duration.ofSeconds(1));

        Flux<String> mergeFlux = Flux.zip(flux1,flux2, (t1,t2)->{
            return t1.concat(t2);
        });

        StepVerifier.create(mergeFlux.log())
                .expectSubscription()
                .expectNext("AE","SR","DT")
                .verifyComplete();

    }


}
