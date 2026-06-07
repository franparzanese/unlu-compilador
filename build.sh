#!/bin/bash

# Limpieza.
rm -rf build/

# Arma el lexer
jflex -d src/flex/ Lexico.flex

# Armar parser luego de hacer cambios en Sintactico.cup:
java -cp lib/java-cup-11b.jar java_cup.Main Sintactico.cup
mv parser.java src/flex/
mv sym.java src/flex/

# Paso 1 - Compilar:
mkdir -p build/classes
javac -cp "lib/*" -d build/classes/ $(find src/ -name "*.java")

# Paso 2 - Mezclar todo:
mkdir -p build/fat
cp -r build/classes/* build/fat/

# PRUEBA:
cd build/fat/
jar xf ../../lib/java-cup-11b-runtime.jar
cd ../../

# Paso 3 - Crear JAR final ejecutable:
jar cfe compilador.jar flex.Main -C build/fat/ .
rm -rf build/
mkdir -p dist
mv compilador.jar dist/
