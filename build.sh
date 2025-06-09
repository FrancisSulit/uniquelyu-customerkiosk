#!/bin/bash

SRC_DIR="src"
BIN_DIR="bin"
LIB_DIR="$SRC_DIR/backend/mysql-connector-j-9.3.0.jar"
MAIN_CLASS="backend.EmployeeMainMenu"

echo "Cleaning previous build..."
rm -rf "$BIN_DIR"
mkdir "$BIN_DIR"

echo "Compiling Java files..."
find "$SRC_DIR" -name "*.java" > sources.txt
javac -cp "$LIB_DIR" -d "$BIN_DIR" @sources.txt
if [ $? -ne 0 ]; then
    echo "Compilation failed."
    exit 1
fi

echo "Compilation complete."
echo "Running $MAIN_CLASS..."
java -cp "$BIN_DIR:$LIB_DIR" $MAIN_CLASS
