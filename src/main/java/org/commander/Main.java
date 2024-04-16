package org.commander;

import org.commander.equipment.mod2.Armory;
import org.commander.equipment.mod2.ModType;
import org.commander.equipment.mod2.Optimizer;

public class Main {
    public static void main(String[] args) {
        optimizeCommanders();
    }
    public static void optimizeCommanders(){
        Armory armory = Armory.load("AwesomeX.txt");
        Optimizer optimizer = new Optimizer(armory, "CommandersNew3.txt");
        optimizer.addRequirement(ModType.RANGED, 180);
        optimizer.addRequirement(ModType.MELEE, 160);
        optimizer.addRequirement(ModType.COURTYARD, 120);
        optimizer.optimize(0.8, 0.8, 0.5, 1, 0.04, 8.2);
        optimizer.writeResults(20);
    }
    public static void optimizeCastellans(){
        Armory armory = Armory.load("CastEquipmentLabelled.txt");
        Optimizer optimizer = new Optimizer(armory, "CastellansNew.txt");
        optimizer.optimize(0.8, 0.8, 0.8, 1, 0.15, 6.0);
        optimizer.writeResults(20);
    }
}