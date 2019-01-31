package cachetest;

import io.micronaut.cache.SyncCache;
import io.micronaut.http.annotation.*;
import io.reactivex.Flowable;
import io.reactivex.Single;

import javax.inject.Inject;

@Controller("/")
public class TestController {
    @Inject
    SyncCache<String> cache;
    @Inject
    ServiceA serviceA;
    @Inject
    ServiceB serviceB;
    @Inject
    ServiceC serviceC;
    @Inject
    ServiceD serviceD;
    String keyA = "A";
    String keyB = "B";
    String keyC = "C";
    String keyD = "D";

    @Get("/readthru")
    public Flowable<String> readthru() {
        return serviceA.get(keyA)
                .mergeWith(serviceB.get(keyB))
                .mergeWith(serviceC.get(keyC))
                .mergeWith(serviceD.get(keyD))
                ;
    }

    @Get("/cached")
    public Flowable<String> cached() {
        return getCachedValue(keyA)
                .mergeWith(getCachedValue(keyB))
                .mergeWith(getCachedValue(keyC))
                .mergeWith(getCachedValue(keyD))
                ;
    }

    private Flowable<String> getCachedValue(String key) {
        return Flowable.just("cached:").map(s -> s + cache.get(key, String.class).orElse("<none>"));
    }
}
