package com.rp.sec01;

import com.rp.courseutil.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class Lec05MonoFromSupplier {

    public static void main(String[] args) {

        // use just only when you have data already
       // Mono<String> mono = Mono.just(getName());
        //Only need to do the work when requested then use Supplier
        //Even if we dont use any subscriber, the method inside Mono.just is called, but if u use Supplier, it is not
        //So things should be Lazy

        
        Supplier<String> stringSupplier = () -> getName();
        Mono<String> mono = Mono.fromSupplier(stringSupplier);
        mono.subscribe(
                Util.onNext()
        );

        Callable<String> stringCallable = () -> getName();
        Mono.fromCallable(stringCallable)
                .subscribe(
                        Util.onNext()
                );


    }

    private static String getName(){
        System.out.println("Generating name..");
        return Util.faker().name().fullName();
    }

}
