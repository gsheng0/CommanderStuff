package org.example.equipment.mod;

public class Mod {
    private ModType modType;
    private int modValue = 0;
    public Mod(ModType modType, int modValue){
        this.modType = modType;
        this.modValue = modValue;
    }
    public int getModValue() { return modValue; }
    public ModType getModType() { return modType; }
    public static Mod parseMod(String mod){
        mod = mod.toLowerCase();
        int value = Integer.parseInt(mod.substring(0, mod.indexOf("%")));
        boolean isConditional = mod.contains("castle lords");

        if (mod.contains("ranged") && isConditional) {
            return new Mod(ModType.CONDITIONAL_RANGED_STRENGTH, value);
        } else if (mod.contains("ranged")) {
            return new Mod(ModType.RANGED_STRENGTH, value);
        } else if (mod.contains("melee") && isConditional) {
            return new Mod(ModType.CONDITIONAL_MELEE_STRENGTH, value);
        } else if (mod.contains("melee")) {
            return new Mod(ModType.MELEE_STRENGTH, value);
        } else if (mod.contains("courtyard") && isConditional) {
            return new Mod(ModType.CONDITIONAL_COURTYARD_STRENGTH, value);
        } else if (mod.contains("courtyard")) {
            return new Mod(ModType.COURTYARD_STRENGTH, value);
        } else if (mod.contains("flank") && isConditional) {
            return new Mod(ModType.CONDITIONAL_FLANK_SPACE, value);
        } else if (mod.contains("flank")) {
            return new Mod(ModType.FLANK_SPACE, value);
        } else if (mod.contains("front") && isConditional) {
            return new Mod(ModType.CONDITIONAL_FRONT_SPACE, value);
        } else if (mod.contains("front")) {
            return new Mod(ModType.FRONT_SPACE, value);
        }
        return null;
    }

}
