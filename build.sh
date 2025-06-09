#!/bin/bash

# Set strict mode
set -e

# Paths
SRC_DIR="src"
BIN_DIR="bin"
MAIN_CLASS="ui.customer.CustomerMain"

# Build classpath from JARs in lib/
LIB_CP=""
for jar in lib/*.jar; do
    if [[ -z "$LIB_CP" ]]; then
        LIB_CP="$jar"
    else
        LIB_CP="$LIB_CP:$jar"
    fi
done

echo "Cleaning previous build..."

rm -rf "$BIN_DIR"
mkdir -p "$BIN_DIR"

echo "Compiling Java files..."

javac -cp "$LIB_CP" -d "$BIN_DIR" \
    "$SRC_DIR/main/dao/"*.java \
    "$SRC_DIR/main/model/"*.java \
    "$SRC_DIR/main/service/"*.java \
    "$SRC_DIR/main/ui/customer/"*.java \
    "$SRC_DIR/main/ui/employee/"*.java \
    "$SRC_DIR/main/utils/"*.java

echo "Compilation complete."
echo "Running $MAIN_CLASS..."
echo

java -cp "$BIN_DIR:$LIB_CP" "$MAIN_CLASS"

EXIT_CODE=$?

if [[ $EXIT_CODE -ne 0 ]]; then
    echo "Program exited with errors (exit code $EXIT_CODE)."
fi

echo
read -n 1 -s -r -p "Press any key to exit..."
echo
