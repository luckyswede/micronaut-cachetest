package cachetest.fallback;

import cachetest.ServiceA;
import cachetest.ServiceB;
import cachetest.ServiceC;
import cachetest.ServiceD;
import io.micronaut.cache.SyncCache;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Flowable;
import io.reactivex.Single;

import javax.inject.Inject;

@Controller("/fallback")
public class TestFallbackController {
    @Inject
    ClientA clientA;
    String value = "somevalue";

    @Get("/test/{key}")
    public Single<String> test(String key) {
        return clientA.get(key);
    }

    @Get("/get")
    public Single<String> get() {
        if (value.equals("fail"))
            throw new RuntimeException();
        return Single.just(value);
    }

    @Get("/set/{value}")
    public Single<String> set(String value) {
        this.value = value;
        return Single.just("ok");
    }

}
