package org.commander;

public class Main {
    public static void main(String[] args) {
        Optimizer opt = new Optimizer("AwesomeX2.txt", "Commanders3.txt");
        opt.optimize(185, 160, 150, 20, 60);
//        OptimizedOptimizer opt = new OptimizedOptimizer("AwesomeX2.txt", "Commanders3.txt");
//        opt.optimize(185, 160, 150, 20, 60);
//        opt.optimize(155, 162, 100, 66);
    }
}