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
