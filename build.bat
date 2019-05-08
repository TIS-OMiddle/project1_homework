@echo off
cd src
javac -d ..\bin -encoding utf-8 algorithm\*.java fileoperator\*.java generator\*.java main\*.java
pause
@echo on