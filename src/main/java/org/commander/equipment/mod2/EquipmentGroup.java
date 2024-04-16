package org.commander.equipment.mod2;

import java.util.HashMap;
import java.util.HashSet;

public class EquipmentGroup implements Comparable<EquipmentGroup>{
    private HashSet<Equipment> equipmentSet;
    private HashMap<ModType, Mod> modTotals = null;
    private ModGroup modGroup;
    private double modScore = 0.0;
    private double waste = 0.0;
    public EquipmentGroup(){
        this.equipmentSet = new HashSet<>();
        modGroup = new ModGroup();
    }
    public void calculateModScore(){
        modScore = 0.0;
        for(Mod mod : modTotals.values()){
            modScore += mod.getModScore();
        }
    }

    public void add(Equipment equipment){
        equipmentSet.add(equipment);

        modGroup.add(equipment.getModGroup());

        calculateModTotals();
    }
    public void add(EquipmentGroup equipmentGroup){
        for(Equipment equipment : equipmentGroup.getEquipmentSet()){
            add(equipment);
        }
        calculateModTotals();
    }
    public HashSet<Equipment> getEquipmentSet(){ return equipmentSet; }
    public ModGroup getModGroup() { return modGroup; }
    public double getModScore() { return modScore; }
    public double getWaste() { return waste; }
    private void calculateModTotals(){
        this.modTotals = new HashMap<>();
        this.waste = 0.0;
        for(Equipment equipment : equipmentSet){
            for(Mod mod : equipment.getModGroup().getModSet()){
                ModType modType = mod.getModType();
                if(!modTotals.containsKey(modType)){
                    modTotals.put(mod.getModType(), new Mod(modType, 0.0, 0.0));
                }
                Mod modTotal = modTotals.get(modType);
                double conditionalBefore = modTotal.getConditionalValue();
                double globalBefore = modTotal.getGlobalValue();
                modTotals.replace(modType, modTotal.add(mod));
                double conditionalAfter = modTotal.getConditionalValue();
                double globalAfter = modTotal.getGlobalValue();
                if(mod.getModType().getGlobalMax() != 0.0){
                    waste += Math.max(0, ((globalAfter - globalBefore) - mod.getGlobalValue())/ modType.getGlobalMax());
                }
                if(mod.getModType().getConditionalMax() != 0.0){
                    waste += Math.max(0, ((conditionalAfter - conditionalBefore) - mod.getConditionalValue()) / modType.getConditionalMax());
                }
            }
        }
        calculateModScore();
    }
    public HashMap<ModType, Mod> getModTotals(){
        return modTotals;
    }

    @Override
    public int compareTo(EquipmentGroup o) {
        return o.modScore == modScore ?
                Double.compare(o.waste, waste) : //need to minimize waste
                Double.compare(o.modScore, modScore); //maximize mod score first
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Mod Score: ");
        builder.append(modScore);
        builder.append("\n");
        builder.append("Waste: ");
        builder.append(waste);
        builder.append("\n");
        for(ModType type : modTotals.keySet()){
            builder.append(modTotals.get(type).toString());
            builder.append("\n");
        }
        for(Equipment equipment : equipmentSet){
            builder.append(equipment.getName());
            builder.append("\n");
        }
        return builder.toString();
    }
    public String getEquipmentList(){
        StringBuilder builder = new StringBuilder();
        for(Equipment equipment : equipmentSet){
            builder.append(equipment.toString());
            builder.append("\n");
        }
        return builder.toString();

    }
}
