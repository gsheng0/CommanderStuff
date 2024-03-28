package org.commander.equipment.mod2;

import java.util.HashSet;

public class Commander implements Comparable<Commander>{
    private HashSet<Equipment> equipmentSet;
    private ModGroup modGroup;
    private double modScore = 0.0;
    private double waste = 0.0;
    public Commander(){
        this.equipmentSet = new HashSet<>();
        modGroup = new ModGroup();
    }
    public void calculateModScore(){
        modScore = modGroup.getModScore();
    }
    public void calculateWaste(){
        waste = modGroup.getWaste();
    }
    public void add(Equipment equipment){
        equipmentSet.add(equipment);
        modGroup.add(equipment.getModGroup());
        calculateWaste();
        calculateModScore();
    }
    public HashSet<Equipment> getEquipmentSet(){ return equipmentSet; }
    public ModGroup getModGroup() { return modGroup; }
    public double getModScore() { return modScore; }
    public double getWaste() { return waste; }

    @Override
    public int compareTo(Commander o) {
        return o.modScore == modScore ?
                Double.compare(o.waste, waste) :
                Double.compare(modScore, o.modScore);
    }
}
