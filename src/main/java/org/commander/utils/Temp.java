package org.commander.utils;

import org.commander.equipment.mod.Equipment;
import org.commander.equipment.mod.Mod;
import org.commander.equipment.mod.ModType;

import java.util.HashMap;
import java.util.List;

public class Temp {
    public static HashMap<String, Double> calculateModTypeTotals(List<Equipment> commander) {
        HashMap<String, Double> totals = new HashMap<>();
        HashMap<String, Double> stats = new HashMap<>();
        for (Equipment equipment : commander) {
            for (Mod mod : equipment.getMods()) {
                String modName = mod.getModType().getName();
                if (!totals.containsKey(modName)) {
                    totals.put(modName, 0.0);
                }
                double before = totals.get(modName);
                totals.replace(modName, Math.min(before + mod.getModValue(), mod.getModType().getMax()));
                double difference = totals.get(modName) - before;
                if (difference != mod.getModValue()) {
                    if (Math.abs(difference - mod.getModValue()) < 0.01) {
                        continue;
                    }
                    String overcappedModName = "overcapped " + modName;
                    if (!stats.containsKey(overcappedModName)) {
                        stats.put(overcappedModName, 0.0);
                    }
                    stats.replace(overcappedModName, stats.get(overcappedModName) + mod.getModValue() - difference);
                }
            }
        }
        return stats;
    }
    public static HashMap<String, Double> calculateStats(List<Equipment> commander){
        HashMap<String, Double> totals = new HashMap<>();
        HashMap<String, Double> stats = new HashMap<>();
        for(Equipment equipment : commander){
            for(Mod mod : equipment.getMods()){
                String modName = mod.getModType().getName();
                if(!totals.containsKey(modName)) {
                    totals.put(modName, 0.0);
                }
                double before = totals.get(modName);
                totals.replace(modName, Math.min(before + mod.getModValue(), mod.getModType().getMax()));
                double difference = totals.get(modName) - before;
                if(difference != mod.getModValue()){
                    if(Math.abs(difference - mod.getModValue()) < 0.01){
                        continue;
                    }
                    String overcappedModName = "overcapped " + modName;
                    if(!stats.containsKey(overcappedModName)){
                        stats.put(overcappedModName, 0.0);
                    }
                    stats.replace(overcappedModName, stats.get(overcappedModName) + mod.getModValue() - difference);
                }
            }
        }
        for(String modName : totals.keySet()){
            double value = totals.get(modName);
            if(modName.startsWith("castle lords")){
                modName = modName.substring("castle lords ".length());
            }
            if(!stats.containsKey(modName)){
                stats.put(modName, 0.0);
            }
            stats.replace(modName, stats.get(modName) + value);
        }
        return stats;
    }
    public static double getMeleeCourtyardStrengthCoefficient(HashMap<String, Integer> stats){
        int baseCourtyard = stats.get(ModType.COURTYARD_STRENGTH.getName());
        int melee = stats.get(ModType.MELEE_STRENGTH.getName());
        int front = stats.get(ModType.FRONT_SPACE.getName());
        int flank = stats.get(ModType.FLANK_SPACE.getName());
        double strengthMultiplier = (1.0 + (baseCourtyard/100.0)) * (1.0 + (melee/100.0));
        double frontMultiplier = (1.0 + (front/100.0));
        double flankMultiplier = (1.0 + (flank/100.0));
        return 1.0;
    }

}
