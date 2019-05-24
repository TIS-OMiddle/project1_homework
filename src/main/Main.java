package main;

import algorithm.Algorithm;
import algorithm.AlgorithmImpl;
import fileoperator.FileOperator;
import generator.ExpressionGenerator;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("请输入参数，如:\n-f expressions.txt\n-g true|false count");
            return;
        }
        Algorithm algorithm = new AlgorithmImpl();
        FileOperator fileOperator = new FileOperator();

        boolean isTranslate = args[0].equals("-f");
        //翻译
        if (isTranslate) {
            fileOperator.setFilepath(args[1]);
            String[] expressions = fileOperator.getexpressions();
            for (String exp : expressions) {
                try {
                    System.out.println(exp + " 转为后缀: " + algorithm.translateToSuffixExp(exp));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {//表达式生成
            boolean isRight = args[1].equals("true");
            int count = Integer.parseInt(args[2]);
            while (count-- != 0) {
                String onPending = ExpressionGenerator.getInfixExpression(isRight);
                fileOperator.expressionoutput(onPending);
            }
        }
    }
}
