# springboot-redis-session-demo
Simple demo with spring boot configuration for redis-persisted sessions. This uses pivotal's [redis-store](https://github.com/pivotalsoftware/session-managers/tree/master/redis-store) session manager. Intended very much as a proof-of-concept and based on the Spring Boot "Hello World" sample.

# Building

Run `gradle build`

# Running

Run `gradle bootRun`. The demo is configured to connect to Redis at `localhost:32768`, the default port assigned by Docker. You 
can override these by setting the `redis.session-store.port` and `redis.session-store.host` properties in `src/main/resources/application.properties`.

# Testing

1. Run the `redis-cli` and the `MONITOR` command
2. Run `curl -v http://localhost:8080/set?value=<any value>`. 
3. Watch the activity on the Redis server
