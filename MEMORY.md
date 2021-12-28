---
title: "TDS"
author: [Mengchen Huang G2.1 2021-2022]
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










# Un diagrama de interacción UML











# Una breve explicación de la arquitectura de la aplicación y decisiones de diseño que se consideren de interés para la comprensión del trabajo.



# La aplicación está dividido en 3 capas, según el modelo de MVC:





- Modelo: 

  - DAO, forma parte de Modelo y se encuentra en paquete `dao`

- Vista:

  - Se encuentra en paquete `gui` donde está la implementación de los ventanas y es el nucleo del vista.

  - En paquete `launcher` se encuentra la entrada del programa principal.

- Controlador:
  - Se encuentra en paquete `controller`, que se encuentra `AppVideo`





# Explicación de los patrones de diseño utilizados (directamente por haberlos implementado o indirectamente por formar parte de alguna librería Java usada como Swing o el servicio de persistencia proporcionado por los profesores de la asignatura).







#  Explicación sobre los componentes utilizados. 









# Tests unitarios implementados.











# Un breve manual de usuario que explique cómo usar la aplicación









# Observaciones finales (deben incluir una estimación del tiempo dedicado a la práctica)
