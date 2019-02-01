package cachetest.fallback;

import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.cache.annotation.CachePut;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

//@CacheConfig("my-cache")
@Client("http://localhost:8080/fallback")
public interface ClientA {

    @CachePut(value = "my-cache", async = true)
    @Get("/get")
    Single<String> get(String key);
}
