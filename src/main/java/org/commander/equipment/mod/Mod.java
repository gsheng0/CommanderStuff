package org.commander.equipment.mod;

import org.commander.equipment.mod2.ModGroup;

import java.text.ParseException;

public class Mod {
    private ModType modType;
    private double modValue;
    public Mod(ModType modType, double modValue){
        this.modType = modType;
        this.modValue = modValue;
    }
    public double getModValue() { return modValue; }
    public ModType getModType() { return modType; }
    public static Mod parseMod(String mod){
        mod = mod.toLowerCase();
        double value = Double.parseDouble(mod.substring(0, mod.indexOf("%")));
        for(ModType modType : ModType.MOD_TYPES) {
            if (mod.contains(modType.getName())) {
                return new Mod(modType, value);
            }
        }
        throw new RuntimeException("Failed to parse mod: " + mod);
    }
    public String toString(){
        return "" + modValue + "% " + modType.toString();
    }

}
