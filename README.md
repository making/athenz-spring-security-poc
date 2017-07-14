```
$ curl localhost:8080/movie -H "X-Athenz-Principal-Auth: valid"
{"name":"Slap Shot","director":"George Roy Hill"}

$ curl localhost:8080/movie -H "X-Athenz-Principal-Auth: invalid"
{"timestamp":1500011450804,"status":403,"error":"Forbidden","exception":"org.springframework.security.access.AccessDeniedException","message":"Access is denied","path":"/movie"}

$ curl localhost:8080/tvshow -H "X-Athenz-Principal-Auth: valid"
{"name":"Middle","channel":"ABC"}

$ curl localhost:8080/tvshow -H "X-Athenz-Principal-Auth: invalid"
{"timestamp":1500011472395,"status":403,"error":"Forbidden","exception":"org.springframework.security.access.AccessDeniedException","message":"Access is denied","path":"/tvshow"}
```