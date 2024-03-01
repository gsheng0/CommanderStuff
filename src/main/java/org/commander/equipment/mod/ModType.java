package org.commander.equipment.mod;

public class ModType {
    private int max = -1;
    private String name = "";
    private ModType(String name, int max){
        this.name = name;
        this.max = max;
    }
    public String getName() { return name; }
    public int getMax() { return max; }
    public static final ModType RANGED_STRENGTH = new ModType("RANGED_STRENGTH", 140);
    public static final ModType MELEE_STRENGTH = new ModType("MELEE_STRENGTH", 140);
    public static final ModType COURTYARD_STRENGTH = new ModType("COURTYARD_STRENGTH", 100);
    public static final ModType FLANK_SPACE = new ModType("FLANK_SPACE", 50);
    public static final ModType FRONT_SPACE = new ModType("FRONT_SPACE", 50);
    public static final ModType CONDITIONAL_RANGED_STRENGTH = new ModType("CONDITIONAL_RANGED_STRENGTH", 50);
    public static final ModType CONDITIONAL_MELEE_STRENGTH = new ModType("CONDITIONAL_MELEE_STRENGTH", 50);
    public static final ModType CONDITIONAL_COURTYARD_STRENGTH = new ModType("CONDITIONAL_COURTYARD_STRENGTH", 60);
    public static final ModType CONDITIONAL_FLANK_SPACE = new ModType("CONDITIONAL_FLANK_SPACE", 40);
    public static final ModType CONDITIONAL_FRONT_SPACE = new ModType("CONDITIONAL_FRONT_SPACE", 40);
    public static final ModType[] MOD_TYPES = new ModType[]{
            RANGED_STRENGTH, MELEE_STRENGTH, COURTYARD_STRENGTH, FLANK_SPACE, FRONT_SPACE,
            CONDITIONAL_COURTYARD_STRENGTH, CONDITIONAL_FLANK_SPACE, CONDITIONAL_FRONT_SPACE,
            CONDITIONAL_MELEE_STRENGTH, CONDITIONAL_RANGED_STRENGTH
    };
}
