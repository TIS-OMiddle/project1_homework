@echo off
cd bin
java -classpath . main.Main -f expressions.txt
@echo on
@echo 找不到文件请输入绝对路径，jvm路径在bin目录下
@echo off
pause