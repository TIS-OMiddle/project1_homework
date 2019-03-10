package main;

import algorithm.Algorithm;
import algorithm.AlgorithmImpl;

public class Main {
    public static void main(String[] args) {
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
    }
}
