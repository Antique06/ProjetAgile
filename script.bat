@echo off
setlocal enabledelayedexpansion

:: Répertoires
set SRC_DIR=src
set BIN_DIR=bin
set LIB_DIR=lib
set RES_DIR=res
set JAR_NAME=CoinCoinQuest-FAT.jar

:: Création du bin
if not exist "%BIN_DIR%" mkdir "%BIN_DIR%"

:: Construction du classpath
set CP="%BIN_DIR%"
for %%f in ("%LIB_DIR%\*.jar") do set CP=!CP!;"%%f"

:: Compilation de tous les fichiers
echo [1/3] Compilation...
set FILES=
for /R "%SRC_DIR%" %%f in (*.java) do (
    set FILES=!FILES! "%%f"
)
javac -d "%BIN_DIR%" -cp !CP! !FILES!
if errorlevel 1 (
    echo ✗ Erreur de compilation
    pause
    exit /b 1
)

:: Copie des ressources dans bin/
echo [2/3] Copie des ressources...
xcopy /E /I /Y "%RES_DIR%" "%BIN_DIR%\res"

:: Création d'un dossier temporaire pour assembler le fat JAR
echo [3/3] Création du fat JAR...
if exist tempJar rmdir /S /Q tempJar
mkdir tempJar
xcopy /E /I /Y "%BIN_DIR%\*" tempJar >nul

:: Décompresse toutes les librairies dans tempJar
cd tempJar
for %%f in ("..\%LIB_DIR%\*.jar") do (
    jar xf "%%f"
)

:: Crée le manifeste
echo Main-Class: main.Main > manifest.txt

:: Crée le fat JAR
jar cfm "..\%JAR_NAME%" manifest.txt *
cd ..
rmdir /S /Q tempJar

echo ✅ Fat JAR créé : %JAR_NAME%
pause
endlocal
