# School-Mito
en este ejemplo se crea un crud con Genericos(repositorios, services, controllers) tambien el uso de routing y handlers pra mongo reactivo validator en webflux y seguridad  
## Mono de Ejecucion
   ```bash
- mvn clean verify
   ```

# School-Mito

Tenemos un ejemplo de cruds con clases genericas tanto para el repository service y controller
tambien el uso de reactividad con Mongo reactive Webflux JAVA 17 Validadores y seguridad

## Requisitos Previos

- JDK 17 o superior
- MongoDB instalado y configurado

## Configuración de la Base de Datos

1. **Crear la base de datos en MongoDB:** - **SCHOOL**
2. Json: **Tips Al final de todo xD**
```json
{
  "_id" : ObjectId("667707a21217d4584f4983a7"),
  "name" : "ADMINISTRADOR"
},
{
  "_id" : ObjectId("667707ab1217d4584f4983a8"),
  "name" : "USUARIO"
}
```

3. Registar dos roles **ADMINISTADOR Y USUARIO**


![img.png](images/img.png)

# Se deja Documento Postman en la raiz ./postman

## Registrar una Persona siempre como USUARIO
![img_1.png](images/img_1.png)

## Authenticarse con el User Creado
![img_2.png](images/img_2.png)

## En caso no se Logeado con su Usuario Correctamente
### para este caso al no tener un token activo este devolvera un Error 401 Unauthorized
![img_3.png](images/img_3.png)

## Enviar Peticion con el Token para Registar una Matricula
![img_4.png](images/img_4.png)
### Token agregado como Cabecera o en Auth el tipo seria Bearer Token.
![img_5.png](images/img_5.png)

## Consultar Esa Matricula por el Id:
![img_6.png](images/img_6.png)

## Consultar Todos los Estudiantes v2 Controller Generico sin filtros

![img_7.png](images/img_7.png)

## Consultar todos los Estudiantes con un OrderBY Asc o Desc

![img_8.png](images/img_8.png)

## Consultar Estudiantes de forma Paginada 
![img_9.png](images/img_9.png)

## Consultar Listado de Courses con el Handler y Routing Reactivo
![img.png](images/img-courses.png)

### Siempre puedes Activar el registar Roles y hacerlo desde postman :)
### Luego eliminas el "/roles" y continuas
![img.png](img.png)