# unlu-compilador

Trabajo práctico de la asignatura **Compiladores** de la Universidad Nacional de Luján.

El proyecto implementa un compilador capaz de realizar análisis léxico y sintáctico, construir un árbol sintáctico abstracto —AST— y generar código assembler compatible con Turbo Assembler.

## Funcionalidades

Actualmente, el lenguaje soporta:

* Declaración de variables `INT`, `FLOAT` y `STRING`.
* Asignaciones numéricas y de cadenas.
* Operaciones aritméticas: suma, resta, multiplicación y división.
* Comparaciones: `>`, `<`, `>=`, `<=`, `==` y `<>`.
* Operadores lógicos: `and`, `or` y `not`.
* Estructuras condicionales `if` y `else`.
* Ciclos `while`.
* Sentencias `PRINT`.
* Sentencia especial `LET ... DEFAULT`.
* Generación del AST en formato DOT y PNG.
* Generación de código assembler.

## Requisitos

Para construir y ejecutar el proyecto se necesita:

* Java JDK.
* JFlex.
* Java CUP.
* Graphviz, para generar la imagen del AST.
* Bash o WSL para ejecutar `build.sh`.
* GUI Turbo Assembler o TASM/TLINK para compilar el assembler generado.

## Uso

Ejecutar el compilador con:

```bash
java -jar dist/compilador.jar
```

Desde la interfaz se puede ingresar o cargar un programa fuente y ejecutar su compilación.

Cuando la compilación es exitosa se generan o actualizan los siguientes archivos:

```text
dist/arbol.dot
dist/arbol.png
dist/assembler.asm
```

## Ejemplo de programa

```text
DECLARE
[a, b, c, d, resultado] := [FLOAT, FLOAT, FLOAT, FLOAT, FLOAT]
ENDDECLARE

BEGIN.PROGRAM

a = 10.5
b = 4.25

LET c:a+b, d, resultado:(a*b)+2.5 DEFAULT 2.5*4

if (resultado > c) {
    PRINT "Resultado es mayor que c"
}
else {
    PRINT "Resultado no es mayor que c"
}

PRINT "Valor de c: "
PRINT c

PRINT "Valor de d: "
PRINT d

PRINT "Resultado: "
PRINT resultado

END.PROGRAM
```

En este ejemplo, la sentencia `LET` realiza las siguientes asignaciones:

```text
c = a + b
d = 2.5 * 4
resultado = (a * b) + 2.5
```

Con los valores iniciales:

```text
a = 10.5
b = 4.25
```

los resultados esperados son:

```text
c = 14.75
d = 10.00
resultado = 47.13
```

## Sentencia LET

La sentencia `LET` permite asignar expresiones a diferentes variables y establecer un valor por defecto para aquellas que no tengan una expresión propia.

```text
LET c:a+b, d, resultado:(a*b)+2 DEFAULT 2.5*4
```

En este caso:

```text
c = a + b
d = 2.5 * 4
resultado = (a * b) + 2
```

La variable `d` no tiene una expresión propia, por lo tanto toma el valor indicado luego de `DEFAULT`.

## Generación del AST

Cada vez que un programa se compila correctamente, se actualiza:

```text
dist/arbol.png
```

El archivo contiene una representación gráfica del árbol sintáctico abstracto del programa.

También se genera:

```text
dist/arbol.dot
```

que contiene la definición del árbol en formato Graphviz DOT.

## Generación del assembler

El código assembler generado se guarda en:

```text
dist/assembler.asm
```

El assembler utiliza instrucciones de la FPU x87 para las operaciones numéricas, por ejemplo:

```asm
FLD _cte10
FLD _cte4
FADD
FSTP resultado
```

Para utilizar las macros de impresión, el archivo generado incluye:

```asm
include macros2.asm
include number.asm
```

El programa debe enlazarse con:

```text
numbers.obj
```

## Compilación del assembler

Primero se debe ensamblar `numbers.asm` para generar:

```text
numbers.obj
```

Luego se ensambla el archivo generado:

```text
assembler.asm
```

Finalmente, se enlazan ambos archivos objeto:

```text
assembler.obj
numbers.obj
```

En GUI Turbo Assembler debe utilizarse la configuración de **Turbo Assembler 16-bit**.

## Desarrollo

Los principales archivos del proyecto son:

```text
Lexico.flex
Sintactico.cup
src/
```

* `Lexico.flex`: definición del analizador léxico.
* `Sintactico.cup`: definición de la gramática y construcción del AST.
* `src/ast/`: nodos del árbol sintáctico.
* `src/flex/`: analizadores generados y clases auxiliares.
* `dist/`: archivos generados por el compilador.

Después de realizar cambios se debe ejecutar:

```bash
./build.sh
```

El script:

1. Genera el analizador léxico con JFlex.
2. Genera el analizador sintáctico con Java CUP.
3. Compila las clases Java.
4. Genera el archivo ejecutable:

```text
dist/compilador.jar
```

En Windows, si aparece el error:

```text
/bin/bash^M: bad interpreter
```

se deben convertir los finales de línea del script:

```bash
sed -i 's/\r$//' build.sh
chmod +x build.sh
./build.sh
```

## Estructura del proyecto

```text
unlu-compilador/
├── Lexico.flex
├── Sintactico.cup
├── build.sh
├── src/
│   ├── ast/
│   └── flex/
└── dist/
    ├── compilador.jar
    ├── assembler.asm
    ├── arbol.dot
    └── arbol.png
```

## Autores

* Facundo Otero.
* Franco Parzanese.
* Franco Zoia.
