# MVC con Servlets, JSP y JDBC
Este proyecto fue creado como base para el desarrollo de la primera práctica integradora del curso de desarrollo backend con Java EE. El sistema a desarrollar es un gestor de notas, las entidades que componen el sistema son Estudiantes, Cursos, Profesores y notas.

## JDBC
Originalmente el proyecto está adecuado para trabajar con PostgreSQL, pero cambiando los valores de la clase `com.academik.mvc.utils.CustomConnection` se puede adecuar cualquier otra base de datos compatible con JDBC (es necesario también agregar la dependencia correspodiente en el archivo `pom.xml`).

## Servidor de aplicaciones
El despliegue del proyecto debe hacerse sobre cualquier servidor de aplicaciones Java, pero idealmente se espera que se trabaje con [Payara 4](https://search.maven.org/artifact/fish.payara.distributions/payara/4.1.2.181/zip) o [Glassfish 4.1.1](https://download.oracle.com/glassfish/4.1.1/release/index.html).

## Tests
Se programó un test para garantizar la correcta conexión a la base de datos, este test se encuentra en `src/test/java/com/academik/mvc/CustomConnectionTest.java` para que el comando Clean and Build de NetBeans funcione correctamente será necesario asegurarse que la clase `CustomConnection.java` se encuentre funcionando correctamente.