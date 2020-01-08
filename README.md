# go-microservices-core-shop
Toy e-commerce with SpringBoot

v01: simple e-commerce app with SpringBoot (backend part only)

Features:
* REST api with Swagger-ui at http://localhost:8086/swagger-ui.html
* in-memory H2 database with some pre-populated data
* basic security with one in-memory ADMIN user (username='admin', password='admin') and USER users stored to database

Use cases:
- anyone can view list of products
- anyone can sign-up (create a new user)
- ROLE_USER can place an order
- ROLE_ADMIN can create/edit products
- ROLE_ADMIN can view and process orders (mark them as paid / shipped)
 