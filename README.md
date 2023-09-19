# PARKING

Este proyecto se centra en la creación de usuarios, parqueaderos y registros.

## Requisitos

- Java 17
- Spring Boot 3
- PostgreSQL
- Postman

## Observaciones
-ARQUITECTURA MVC - REST
-En el proyecto envio 2 repositorios, uno donde tengo funcional la API rest sin spring security y otro donde intento hacerla funcionar con security, tuve algunos problemas implementandolo ya que como tal es la primera vez que lo hago, realizando un log de la app si me recupera el usuario, pero cuando intento hacer el login me envia la excepcion.

![1](https://github.com/DuvanLabrador27/Parking/assets/80419064/9e7ae2b1-d99d-4042-8b7e-4942bdff64ef)
![2](https://github.com/DuvanLabrador27/Parking/assets/80419064/58979dd3-7e20-453e-8080-3d7f324a3c19)
![3](https://github.com/DuvanLabrador27/Parking/assets/80419064/a2542b2d-1fb2-4b9b-824c-88a781d04cb3)

-En el repositorio uno que es la api la cual funciona como tal, la forma la cual se me ocurrio para precargar un user fue utilizando un archivo sql y la configuración CREATE-DROP, cada vez que se inicia la app automaticamente inserta el registro para poder probar la api.
![4](https://github.com/DuvanLabrador27/Parking/assets/80419064/f75ae581-add0-43df-8be3-19bd8379bc5e)
-En el repositorio 2 encontre la forma de usarla sin el import sql, ya que con spring security pude configurarlo para precargar el usuario.


## Autor

Carlos Duvan Labrador Hernandez
