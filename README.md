# Technical Test API

Esta API es una solución técnica construida con Spring WebFlux y empaquetada usando Docker para facilitar su despliegue. Utiliza R2DBC para conectividad reactiva con PostgreSQL y Flyway para migraciones de base de datos.


## Instalación y Ejecución

La imagen Docker ya está publicada en Docker Hub y puede ejecutarse fácilmente con el siguiente comando:

```bash
docker run -p 8081:8081 -e R2DBC_URL="r2dbc:postgresql://host.docker.internal:5432/nequi_technical_test" -e JDBC_URL="jdbc:postgresql://host.docker.internal:5432/nequi_technical_test" -e DB_USERNAME="postgres" -e DB_PASSWORD="root" ccarmona9402/technical-test-api:v1
```
Es importante tener en cuenta que para correrlo en local es necesario tener una base de datos llamada nequi_technical_test en el puerto 5432 o crear otra conexión y cambiar los parametros de ejecución del contenedor.

Se adjunta la colección en postman en el repositorio.
