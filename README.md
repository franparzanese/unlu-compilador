# unlu-compilador

Trabajo práctico de la asignatura "Compiladores" de la Universidad Nacional de Luján.

## Uso

```
java -jar dist/compilador.jar
```

### Generar el AST

Al ejecutar el compilador, cada vez que se compile una entrada exitosamente se actualizará la imagen `dist/arbol.png` con el árbol del código parseado.

## Desarrollo

Trabajar sobre los ficheros `Lexico.flex`, `Sintactico.cup` y todos los que estén dentro del directorio `src/`. Al terminar se debe hacer el build del proyecto:

```
./build.sh
```

Este script genera los analizadores léxico y sintáctico, y compila todo para generar el ejecutable `dist/compilador.jar`.

## Autores

* Facundo Otero.
* Franco Parzanese.
* Franco Zoia.
