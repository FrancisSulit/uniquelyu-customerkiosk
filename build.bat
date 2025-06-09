@echo off
setlocal

REM Paths
set SRC_DIR=src
set BIN_DIR=bin

set LIB_CP=
for %%j in (lib\*.jar) do (
    set LIB_CP=!LIB_CP!;%%j
)

set MAIN_CLASS=ui.customer.CustomerMain

echo Cleaning previous build...

if exist %BIN_DIR% (
    rmdir /s /q %BIN_DIR%
)
mkdir %BIN_DIR%

echo Compiling Java files...

javac -cp "%LIB_CP%" -d %BIN_DIR% ^
    %SRC_DIR%\main\dao\*.java ^
    %SRC_DIR%\main\model\*.java ^
    %SRC_DIR%\main\service\*.java ^
    %SRC_DIR%\main\ui\customer\*.java ^
    %SRC_DIR%\main\ui\employee\*.java ^
    %SRC_DIR%\main\utils\*.java

if errorlevel 1 (
    echo Compilation failed.
    goto end
)

echo Compilation complete.
echo Running %MAIN_CLASS%...
echo:

java -cp "%BIN_DIR%;%LIB_CP%" %MAIN_CLASS%

if errorlevel 1 (
    echo Program exited with errors.
)

:end
echo.
echo Press any key to exit...
pause > nul
