Para ejecutar el proyecto no hay que preocuparse por la creación de la base de datos, pues se trata de una base
de datos h2 que se cargará en memoria cada vez que se inicie la aplicación y cargará datos en ella gracias a
los archivos alojados en la carpeta resources schema.sql y data.sql

Al clonar el repositorio simplemente habrá que esperar a que se descarguen todas las dependencias de maven y ya
se podrá ejecutar la api para realizar peticiones a los distintos endpoints. Adjunto en la carpeta raiz del proyecto
(ApiReservas.requestCollection) la colección de postman que he utilizado para testear la api Rest. Esta contiene todos los endpoints disponibles en la
api con datos de ejemplo.

He incluido el plugin jacoco para visualizar la cobertura de código de los test, para verla, habrá que ir a
target/site/index.html tras ejecutar el comando mvn test en el terminal.

Dicho archivo es una web en la que se muestran los informes de cobertura de código y como han ido los test.