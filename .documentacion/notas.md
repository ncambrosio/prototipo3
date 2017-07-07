# Desarrollo

* iniciar backend con spring-boot: mvn spring-boot:run
* iniciar frontend con npm start (que utiliza 'ng serve')

De esta forma atacamos el backend empleando AngularCli para desplegar (y redesplegar cuando detecta cambios) la aplicación frontend (angular2).


# OAUTH2

### Entendiendo la diferencia (authorization vs authentication)

* https://oauth.net/articles/authentication/
* https://stormpath.com/blog/oauth-is-not-sso

### Resharevoir

* https://spring.io/guides/tutorials/spring-boot-oauth2/#_social_login_simple
Este es el tutorial que se ha seguido en la aplicación.

### Otros recurso interesantes:

* http://www.baeldung.com/rest-api-spring-oauth2-angularjs
Aquí lo interesante es que implementa un servidor de autorización propio, es decir, un servidor que autoriza de la misma forma que Facebook o Github autoriza en nuestra implementación.