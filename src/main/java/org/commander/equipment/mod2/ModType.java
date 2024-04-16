package org.commander.equipment.mod2;

import java.util.Objects;

public class ModType {
    private String modName;
    private double globalMax = 0.0;
    private double conditionalMax = 0.0;
    private ModType(String modName, double globalMax, double conditionalMax){
        this.modName = modName;
        this.globalMax = globalMax;
        this.conditionalMax = conditionalMax;
    }
    public String toString(){
        return modName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModType modType = (ModType) o;
        return Double.compare(modType.globalMax, globalMax) == 0 && Double.compare(modType.conditionalMax, conditionalMax) == 0 && Objects.equals(modName, modType.modName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modName, globalMax, conditionalMax);
    }
    public static ModType parseModType(String string){
        string = string.toLowerCase();
        for(ModType modType : MOD_LIST){
            if(string.contains(modType.modName)){
                return modType;
            }
        }
        throw new RuntimeException("Error parsing: " + string);
    }

    public double getGlobalMax() { return globalMax; }
    public double getConditionalMax() { return conditionalMax; }
    public static ModType RANGED = new ModType("ranged", 140.0, 50.0);
    public static ModType MELEE = new ModType("melee", 140.0, 50.0);
    public static ModType COURTYARD = new ModType("courtyard", 100.0, 60.0);
    public static ModType FLANK = new ModType("flank", 50.0, 40.0);
    public static ModType FRONT = new ModType("front", 50.0, 40.0);
    public static ModType COMBAT_STRENGTH = new ModType("combat", 20.0, 0.0);
    public static ModType WALL_SPACE = new ModType("wall space", 50.0, 40.0);
    public static ModType[] MOD_LIST = new ModType[]{
            RANGED, MELEE, COURTYARD, FLANK, FLANK, FRONT, COMBAT_STRENGTH, WALL_SPACE
    };

}
