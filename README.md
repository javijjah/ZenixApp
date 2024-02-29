---
description: Información general de la App
---

# 🧘‍♀️ Zenix App

Una app de meditación grupal.

## Concepto de la aplicación

Zenix es una app de meditación, la cual nos permite realizar sesiones de meditación grupales sincronizadas. Utiliza el login de Google para mayor comodidad del usuario y no tener que recordar logins

## Construcción en Figma

El archivo en Figma tiene todos los colores como variables, lo que permite fácilmente cambiar el visualizado de toda la aplicación.

## Elementos de UI

La UI ha sido gestionada a través del componente Relay for Figma, la cual genera componentes prácticamente iguales a los creados en Figma. La app ahorra mucho trabajo a nivel de trabajo en la UI pero hay que saber manejar estos componentes para que se comporten como deseamos, como colocar dentro del Modifier.size() el tamaño exacto que tenemos en Figma o incluir Auto Layout para poder centrarlo. Este componente también incluye RelayColumn y RelayRow para ordenar sus componentes.

