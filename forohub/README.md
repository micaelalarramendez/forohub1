# ForoHub
API REST desarrollada para el challenge **ForoHub**, con autenticación mediante **JWT** y CRUD completo de tópicos.

## Tecnologías
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- MySQL
- JWT (`com.auth0:java-jwt:4.5.1`)


### Autenticación
- `POST /login`

### Endpoints
- `GET /topicos`
- `GET /topicos/{id}`
- `POST /topicos`
- `PUT /topicos/{id}`
- `DELETE /topicos/{id}`

## Acceso

Para usar los endpoints de `/topicos`:

1. hacer login en `POST /login`
2. copiar el token recibido
3. enviarlo como **Bearer Token**

## Base de datos

Se utilizó **MySQL**.

### Usuario de prueba
- `login = admin`
- `clave = 123456`

### Campos de `topicos`
- `id`
- `titulo`
- `mensaje`
- `autor`
- `curso`

## Ejecución

1. clonar o descargar el proyecto
2. crear la base de datos `forohub`
3. configurar `application.properties`
4. ejecutar la aplicación
5. probar `POST /login`
6. usar el token para probar `/topicos`

## Reglas implementadas
- todos los campos del tópico son obligatorios
- no se permiten tópicos duplicados con el mismo `titulo` y `mensaje`
- la consulta individual se realiza por `id`

## Pruebas en Postman

Se verificaron los siguientes casos:

1. login exitoso
2. creación de tópico
3. validación de duplicado
4. listado de tópicos
5. consulta por id
6. actualización
7. eliminación
8. listado final

## Capturas

### 1. Login exitoso
![Login exitoso](img/01-login.png)

### 2. Creación de tópico
![Creación de tópico](img/02-post-topico.png)

### 3. Validación de duplicado
![Validación de duplicado](img/03-post-duplicado.png)

### 4. Listado de tópicos
![Listado de tópicos](img/04-get-topicos.png)

### 5. Consulta por id
![Consulta por id](img/05-get-topico-id.png)

### 6. Actualización

![Actualización](img/06-put-topico.png)

### 7. Eliminación

![Eliminación](img/07-delete-topico.png)

### 8. Listado final

![Listado final](img/08-get-final.png)

## Errores comunes

- `401 Unauthorized`: credenciales incorrectas
- `403 Forbidden`: token ausente o inválido
- `400 Bad Request`: datos incompletos o vacíos
- `409 Conflict`: tópico duplicado
- `ECONNREFUSED`: aplicación apagada o puerto incorrecto
- error de MySQL: revisar base, usuario, contraseña, puerto y `application.properties`

## Autor
Micaela Larramendez
