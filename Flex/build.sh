#!/bin/bash

# Armar parser luego de hacer cambios en Sintactico.cup:
java -cp libraries/java-cup-11b.jar java_cup.Main ../Sintactico.cup

# Paso 1 - Compilar:
mkdir -p build/classes
javac -cp "libraries/*" -d build/classes $(find src -name "*.java")

# Paso 2 - Mezclar todo:
mkdir -p build/fat
cp -r build/classes/* build/fat/

# Paso 3 - Crear JAR final ejecutable:
jar cfe app.jar flex.PruebaAST -C build/fat .