package cachetest;

import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.cache.annotation.CachePut;
import io.reactivex.Single;

import java.util.concurrent.atomic.AtomicInteger;

@CacheConfig("my-cache")
public class ServiceA {

    AtomicInteger count = new AtomicInteger(0);

    @CachePut
    public Single<String> get(String key) {
        return Single.just(key + "->" + count.incrementAndGet());
    }
}
