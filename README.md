# PokemonApi

- Aplicación basada en SpringBoot/JPA/H2

### Tarea solicitada
Show in JSON via HTTP's request the following scenarios:

- The 5 heaviest Pokemons.
- The 5 highest Pokemons.
- The 5 Pokemons with more base experience.

To know this information, you must create a platform based on micro-services with the following prerequisites:

- Use Java/SpringBoot
- JUnit tests
- Use PokeAPI: https://pokeapi.co/api/v2/

### Aproximación a la tarea
PokeAPI sólo suministra información, no la trabaja. Inicialmente parece un problema sencillo, se obtiene el listado y
se ordena. El problema aparece cuando se hace recuento de los pokemons y, por un lado son 1126 (en estas fechas) y por
el otro lado, para obtener los datos solicitados se debe hacer una petición por cada uno de ellos, para obtenerla.

Para acceder a la información de cada uno de los pokemons hay que hacer una petición a la url correspondiente:
"https://pokeapi.com/api/v2/pokemon/{id}".

Esta información se descarga con una petición a "https://pokeapi.com/api/v2/pokemon/".

Debido al número de peticiones los tiempos de espera son bastante altos.

Sólo se me han ocurrido estas maneras de realizar la tarea:

- (A) Consulta y tratamiento directo en cada sección.
- (B) Obtener la información y archivarla en base de datos y acceder desde query.
- (C) Obtener la información y archivarla en base de datos de una forma más permanente y acceder desde query.

La (A) creo que es la que más tiempo requiere para presentar al usuario, ya que si se tardan 10 min en obtener, por
cada consulta, a la larga se hará excesivamente pesado. La (C) aunque sólo tardaría la primera vez de ejecución,
conlleva un mantenimiento a largo plazo de la base de datos, teniendo que programar tareas para la actualización
regular de la información.

Debido a este razonamiento, la opción que voy a desarrollar es la (B). Inicialmente realizaré una consulta de toda la
información y la guardaré en una base de datos temporal, que servirá para todas las consultas que se deseen realizar y
posteriormente.

Aunque se anima a usar tecnologías como docker, en este caso creo que es más óptimo el uso de una base de datos más
portable y económica de recursos. Sería la opción elegida si se desarrollara (C).

### Uso
Acceso de la aplicación "http://localhost:8080/api/v1/pokemon/". (La primera carga es lenta, aprox. 10min, aparecerá 
mensaje cuando se termine).

Base de datos en el directorio ~/pokemonApp.mv.db

w = weight
h = height
e = base experience