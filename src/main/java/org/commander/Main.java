package org.commander;

public class Main {
    public static void main(String[] args) {
        Optimizer opt = new Optimizer("AwesomeX.txt");
        opt.optimize(140, 140, 100, 0, 40);
    }
}