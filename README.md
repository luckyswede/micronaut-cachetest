#### Cache inject dont work as expected 

Start server
```
gradle run
```
Invoke the the test endpoint
```
curl localhost:8080/fallback/test/a
somevalue
```
Make the next request fail by setting state
```
curl localhost:8080/fallback/set/fail
ok
```
Invoke the test endpoint again

```
curl localhost:8080/fallback/test/a
somevalue
```
and the fallback returns the cached value as expected.

But now comment out `@CacheConfig` in the `ClientA` class, like `//@CacheConfig("my-cache")` and rerun the tests.
This time there is no value in the injected cache in `ClientAFallback`
Adding `@CacheConfig("my-cache")` to `ClientAFallback` does not help, neither does adding `@Named("my-cache")` to the cache field.


#### CachePut async error 
*this issue is resolved by https://github.com/micronaut-projects/micronaut-core/issues/1197*

Start server
```
gradle run
```
Invoke the readthru endpoint
```
curl localhost:8080/readthru
[{},B->1,C->1,D->1]
```
and `serviceA` seems to return an Object instead of the expected String.

Invoke the cache endpoint

```
curl localhost:8080/cached
[cached:A->1,cached:B->1,cached:<none>,cached:<none>]
```
and there are only 2 cached values, when 4 are expected.
