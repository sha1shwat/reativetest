package com.reactive.reactivespring.playground;

import org.junit.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class PublisherTest {

    List<String> arr = Arrays.asList("A","D","L","G","W");


    @Test
    public void coldPus() throws InterruptedException {


        Flux<String> stringFlux = Flux.fromIterable(arr).delayElements(Duration.ofSeconds(1));

        stringFlux.subscribe((s)-> System.out.println("Df1 : "+s));

        Thread.sleep(2000);

        stringFlux.subscribe((s)-> System.out.println("Df2 : "+s));

        Thread.sleep(4000);


    }

    @Test
    public void hotPus() throws InterruptedException {


        Flux<String> stringFlux = Flux.fromIterable(arr).delayElements(Duration.ofSeconds(1));

         ConnectableFlux<String> stringConnectableFlux = stringFlux.publish();
         stringConnectableFlux.connect();

        stringConnectableFlux.subscribe((s)-> System.out.println("Df1 : "+s));

        Thread.sleep(3000);

        stringConnectableFlux.subscribe((s)-> System.out.println("Df2 : "+s));

        Thread.sleep(4000);


    }
}
