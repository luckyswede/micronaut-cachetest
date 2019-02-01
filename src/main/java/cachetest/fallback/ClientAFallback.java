package cachetest.fallback;

import io.micronaut.cache.SyncCache;
import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.retry.annotation.Fallback;
import io.reactivex.Single;

import javax.inject.Inject;

@CacheConfig("my-cache")
@Fallback
public class ClientAFallback implements ClientA {
    @Inject
    SyncCache<String> cache;

    public Single<String> get(String key) {
        return Single.just(cache.get(key, String.class).orElseThrow());
    }
}
