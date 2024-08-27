# Product Pricing Service
This is an implementation of Java Assignment with following [requirements](/Java%20Assignment%201.pdf).
It's a service written in Java 21, latest (at the time of writing) Spring Boot version, Hibernate & Postgres SQL for persistence.

### Running app locally & containerization
Having Docker up & running is required to run the app:
```shell
./gradlew runLocally
```
Service can be containerized, utilize bootBuildImage gradle task:
```shell
./gradlew bootBuildImage
```

### API Documentation 
API doc is exposed through Swagger and can be accessed under following link:
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) <br>
Endpoints should be self-explanatory for anyone with REST API experience.

### Assumptions
- For the sake of simplicity, pricing, products, and discounts are in the same service - no external calls within the app
- Discounts are global and can be applied to any persisted product
- Discount is not obligatory for total price calculation
- Only one discount can be applied for a product
- No currency logic - the application assumes every price reference is in USD
- No security layer
- Calculated total price has to be greater than 0


