@echo off
cd bin
java -classpath . main.Main -g false 10
@echo on
@echo 生成的表达式(true)在bin目录下
@echo off
pause