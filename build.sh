#!/bin/bash

set -euo pipefail

# Genera el analizador léxico.
jflex --nobak -d src/flex/ Lexico.flex

# Genera el analizador sintáctico.
java -cp lib/java-cup-11b.jar java_cup.Main Sintactico.cup
mv parser.java sym.java src/flex/

# Compila el proyecto.
mkdir build
javac -cp "lib/java-cup-11b-runtime.jar" -d build/ $(find src/ -name "*.java")
(
    cd build/
    jar xf ../lib/java-cup-11b-runtime.jar
)

# Genera el JAR ejecutable.
jar cfe dist/compilador.jar flex.Main -C build/ .
rm -rf build/

echo "Build completado. El ejecutable se encuentra en dist/compilador.jar."
