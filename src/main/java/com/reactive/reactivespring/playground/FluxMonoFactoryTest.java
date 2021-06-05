package com.reactive.reactivespring.playground;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class FluxMonoFactoryTest {

    List<String> iter = Arrays.asList("ASDc","ASDWE","as");

    @Test
    public void fluxIterativeTest(){

        Flux<String> stringFlux = Flux.fromIterable(iter);
        StepVerifier.create(stringFlux)
                .expectNext("ASDc","ASDWE","as")
                .verifyComplete();
    }

    @Test
    public void fluxArrayTest(){

        String[] arr = new String[]{"ASDc","ASDWE","as"};
        Flux<String> arrFlux = Flux.fromArray(arr);
        StepVerifier.create(arrFlux)
                .expectNext("ASDc","ASDWE","as")
                .verifyComplete();
    }

    @Test
    public void monoSupplierTest(){

        Supplier<String> dumString = ()->"fgd";
        StepVerifier.create(Mono.fromSupplier(dumString))
                .expectNext("fgd")
                .verifyComplete();


    }
}
