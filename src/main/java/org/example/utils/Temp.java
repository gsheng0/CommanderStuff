package org.example.utils;

import org.example.equipment.Equipment;
import org.example.equipment.mod.Mod;
import org.example.equipment.mod.ModType;
import org.example.equipment.Gem;

import java.util.HashMap;
import java.util.List;

public class Temp {
    public static HashMap<String, Integer> calculateStats(List<Equipment> commander){
        HashMap<String, Integer> totals = new HashMap<>();
        HashMap<String, Integer> stats = new HashMap<>();
        for(Equipment equipment : commander){
            for(Mod mod : equipment.getMods()){
                String modName = mod.getModType().getName();
                if(!totals.containsKey(modName)) {
                    totals.put(modName, 0);
                }
                totals.replace(modName, Math.min(totals.get(modName) + mod.getModValue(), mod.getModType().getMax()));
            }
        }
        for(String modName : totals.keySet()){
            int value = totals.get(modName);
            if(modName.startsWith("CONDITIONAL_")){
                modName = modName.substring("CONDITIONAL_".length());
            }
            if(!stats.containsKey(modName)){
                stats.put(modName, 0);
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