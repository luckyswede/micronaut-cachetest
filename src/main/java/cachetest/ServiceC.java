package cachetest;

import io.micronaut.cache.annotation.CachePut;
import io.reactivex.Single;

import java.util.concurrent.atomic.AtomicInteger;

@CachePut
public class ServiceC {

    AtomicInteger count = new AtomicInteger(0);

    public Single<String> get(String key) {
        return Single.just(key + "->" + count.incrementAndGet());
    }
}
