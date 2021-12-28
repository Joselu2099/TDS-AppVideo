---
title: "TDS"
author: [Mengchen Huang G2.1 && Jose Luis Sánchez Carrasco G3.1  2021-2022]
subject: "TDS"
subtitle: "AppVideo"
titlepage: true
keywords: [TDS,JAVA,SWING,APP]
toc: true
toc-own-page: true
mainfont: Avenir LT Pro
sansfont: Adelle
header-includes:
  - \setsansfont[Path=/Users/mc/Library/Fonts/ManualInstalled/,
                              BoldFont=*_Semibold,
                              ItalicFont=*_LightItalic,
                              BoldItalicFont=*_SemiBoldItalic,
                              Extension = .otf,
                             ]{Adelle}
monofont: Hack Regular Nerd Font Complete Mono
lang: "es"
colorlinks: true
urlcolor: "ogange"


---


# Diagrama de clases del dominio
![Diagrama de clases del dominio](images/AppVideo DC.pdf)

# Un diagrama de interacción UML

# Arquitectura y diseño de la aplicación

La aplicación consta de 46 clases para las ventanas que formarán parte de la capa de vistas (paguete `gui`), los adaptadores para las diferentes entidades que se guardan en persistencia (paquete `dao`), las clases del dominio (paquete `model`), el lanzador (paquete `laucher`) y el controlador `AppVideo` (paquete `controller`). En la parte de GUI tenemos diversas clases desarrolladas mas alla de las propias ventanas como Utils, nuevos Layouts y previews para mostrar los videos y las playlist.

![Arquitectura y diseño de la app](images/diseñoAPP.png)

Otro elemento a mencionar es el componente cargador de videos, que consta de 6 clases, 5 ya venian dadas y la sexta es el componente java bean al cual hemos llamado `VideosLoader`, el cual utilizando el patron Observer carga los videos de un XML dado y notifica a los oyentes con los videos que ha cargado. 

# Patrones de diseño
## Patrones de diseño utilizados directamente
La mayoría de los patrones los hemos usado en el servicio de persistencia:
  - Patrón DAO. Usado para persistir usuarios, videos y playlist.
  - Patrón adaptador. Usado con el servicio de persistencia para que la aplicación dependa de una interfaz con los métodos para acceder al servicio de persistencia.
  - Patrón factoría abstracta. Usado también con el servicio de persistencia.
  - Patrón singleton. Usado en los repositorios, en el servicio de persistencia y en el controlador.
  - Patrón fachada. Usado en el controlador (`controller`).
  - Patrón observer, para cargar videos, para modificar playlist, videos o aplicar filtros y notificar el cambio al panel `MyPlaylistPanel` y `HomePanel`.
## Patrones de diseño utilizados indirectamente
Los patrones de diseño que hemos usado indirectamente al usar Java AWT, Swing y los botones, listas y el desarrollo de las ventanas gráficas son:
  - Patrón bridge.
  - Patrón adaptador.
  - Patrón composite.
  - Patrón decorador.
  - Patrón estrategia.
  - Patrón iterador.
  - Patrón observer.
  
#  Explicación sobre los componentes utilizados. 

Nuestra aplicación hace uso de dos componentes, el primero llamado `luz` se puede encontrar en la ventana `AppVideoWindow`, en concreto en `HomePanel`, es un componente sencillo que al ser pulsado emite una luz de un color, en nuestro caso hemos puesto un color amarillo como luz, y a continuacion se abre un selector de archivos en el que debes seleccionar el `XML` en el que se encuentras las canciones, en nuestro caso se llama `videos.xml`. Una vez seleccionado el archivo el boton vuelve a su estado por defecto y deja de estar pulsado.

El segundo componente está directamente relacionado con este componente `luz`, el cual hemos visto que su funcionalidad es elegir un archivo `xml` donde estan los videos que queremos cargar, tras elegir ese archivo hacemos uso de este segundo componente llamado `VideosLoader`, el cual pasandole este archivo `xml` como parametro carga los videos haciendo un mapeo de `xml` a `java`. Una vez cargados los videos por el componente notifica al oyente haciendo uso del patron observer, en este caso `controller` (el controlador) con los videos cargados.

# Tests unitarios implementados.











# Un breve manual de usuario que explique cómo usar la aplicación









# Observaciones finales
Esta práctica nos ha enseñado a enfrentarnos a un proyecto de gran envergadura, por lo menos en comparación de todas las prácticas anteriores realizadas en la carrera, perfectamente podría tratarse una aplicación comercial, más allá de que es mejorable en diversos aspectos, si fuese una aplicación que tuviese que competir en el mercado de hoy en día, obviando eso AppVideo se parece a una aplicación real.
A la hora del desarrollo hemos tenido errores a la hora de como afrontar el diseño software de ciertas partes del programa como la implementacion del componente `VideosLoader` a la hora de hacer uso de el junto al componente `Luz`, pero que hemos solucionado con facilidad. También hemos tenido que aplicar mucho refactoring a lo largo de todo el desarrollo, una vez mas por temas de diseño a la hora de revisar que no incumpliese los patrones `GRASP`, de que se respetase el patrón `modelo-vista`. También de como llevar a cabo ciertas funcionalidades como crear playlist y editarlas, ya que hay varias formas de hacerlo. En resumen, decisiones de diseño que hemos ido tomando y cambiando a lo largo del desarrollo que nos ha llevado muchas horas de refactorización.
## Conclusiones
Hemos aprendido mucho sobre patrones de diseño y desarrollo software en general realizando este práctica. Hemos aprendido a utilizar swing, a diseñar interfaces gráficas, la separacion en capas de la aplicacion (`modelo-vista`), adaptadores, factorias abstractas y patrón `dao` para usar la base de datos, uso de `layouts`, etc.

## Horas estimadas
|          Alumnos           |      GUI      |  Modelo | Persistencia | Componentes | Refactoring/Mejoras | Utils | Memoria | Horas estimadas |
|----------------------------|:-------------:|--------:| ------------:| -----------:| -------------------:| -----:| -------:| ---------------:|
| Jose Luis Sanchez Carrasco |  60           |  15     | 10           | 5           | 70                  | 2     | 5       | 167             |
| Mengchen Huang             |  65           |  5      | 15           | 4           | 80                  | 5     | 5       | 179             |
