package main;

import java.util.ArrayList;

import algorithm.Algorithm;
import algorithm.AlgorithmImpl;
import fileoperator.FileOperator;
import generator.ExpressionGenerator;

public class Main {
    public static void main(String[] args) {
   // API: ExpressionGenerator.getInfixExpression(true)
   // true 表示正确  false表示错误
    	
    	// 随机生成 + 中-》后测试
    	int i = 10;
    	
    	Algorithm algorithm = new AlgorithmImpl();
    	FileOperator fileOperator=new FileOperator();
    	try {
    		while( i-- != 0 ) {
	    		String onPending = ExpressionGenerator.getInfixExpression(false);
	    		fileOperator.expressionoutput(onPending); 	
	    	//	System.out.println(algorithm.translateToSuffixExp(onPending));
    		}
    		ArrayList<String> expressions=fileOperator.getexpressions();
    		while( i++ != expressions.size() ) {	    		
	    		System.out.println(algorithm.translateToSuffixExp(expressions.get(i)));
    		}
    	} catch (Exception e) {
            System.out.println(e.getMessage());
        }
    	
    /*	表达式生成测试
    	int i = 10;
    	while( i-- != 0 ) {
    		System.out.println(ExpressionGenerator.getInfixExpression(true));
   		}	
    */
    	
    	
    /*		中-》后测试
	        Algorithm algorithm = new AlgorithmImpl();
	        //样例1
	        try {
	            System.out.println(algorithm.translateToSuffixExp("12.5+7*4-8/6"));
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        //样例2
	        try {
	            System.out.println(algorithm.translateToSuffixExp("(1+2)-(3+(-4))"));
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
     */
    	  	
    }
}
