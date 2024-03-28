package org.commander.equipment.mod;

public class ModType {
    private int max = -1;
    private String name = "";
    private ModType(String name, int max){
        this.name = name;
        this.max = max;
    }
    public String getName() {
        return name;
    }
    public int getMax() {
        return max;
    }
    @Override
    public String toString() {
        if (name.startsWith("CONDITIONAL")) {
            String[] parts = name.split("_");
            StringBuilder builder = new StringBuilder("castle lords");
            for (int i = 1; i < parts.length; i++) {
                builder.append(" ").append(parts[i].toLowerCase());
            }
            return builder.toString();
        } else {
            return name.toLowerCase();
        }
    }
    public static final ModType COMBAT_STRENGTH = new ModType("combat strength", 1000);
    public static final ModType RANGED_STRENGTH = new ModType("ranged", 140);
    public static final ModType MELEE_STRENGTH = new ModType("melee", 140);
    public static final ModType COURTYARD_STRENGTH = new ModType("courtyard", 100);
    public static final ModType FLANK_SPACE = new ModType("flank", 50);
    public static final ModType FRONT_SPACE = new ModType("front", 50);
    public static final ModType WALL_SPACE = new ModType("wall", 50);
    public static final ModType CASTLE_LORDS_COURTYARD = new ModType("castle lords courtyard", 60);
    public static final ModType CASTLE_LORDS_FLANK = new ModType("castle lords flank", 40);
    public static final ModType CASTLE_LORDS_FRONT = new ModType("castle lords front", 40);
    public static final ModType CASTLE_LORDS_MELEE = new ModType("castle lords melee", 50);
    public static final ModType CASTLE_LORDS_RANGED = new ModType("castle lords ranged", 50);
    public static final ModType CASTLE_LORDS_WALL = new ModType("castle lords wall", 40);
    public static final ModType OVERCAPPED_COMBAT_STRENGTH = new ModType("overcapped combat strength", 1000);
    public static final ModType OVERCAPPED_RANGED_STRENGTH = new ModType("overcapped ranged", 140);
    public static final ModType OVERCAPPED_MELEE_STRENGTH = new ModType("overcapped melee", 140);
    public static final ModType OVERCAPPED_COURTYARD_STRENGTH = new ModType("overcapped courtyard", 100);
    public static final ModType OVERCAPPED_FLANK_SPACE = new ModType("overcapped flank", 50);
    public static final ModType OVERCAPPED_FRONT_SPACE = new ModType("overcapped front", 50);
    public static final ModType OVERCAPPED_WALL_SPACE = new ModType("overcapped wall", 50);
    public static final ModType OVERCAPPED_CASTLE_LORDS_COURTYARD = new ModType("overcapped castle lords courtyard", 60);
    public static final ModType OVERCAPPED_CASTLE_LORDS_FLANK = new ModType("overcapped castle lords flank", 40);
    public static final ModType OVERCAPPED_CASTLE_LORDS_FRONT = new ModType("overcapped castle lords front", 40);
    public static final ModType OVERCAPPED_CASTLE_LORDS_MELEE = new ModType("overcapped castle lords melee", 50);
    public static final ModType OVERCAPPED_CASTLE_LORDS_RANGED = new ModType("overcapped castle lords ranged", 50);
    public static final ModType OVERCAPPED_CASTLE_LORDS_WALL = new ModType("overcapped castle lords wall", 40);
    public static final ModType[] MOD_TYPES = new ModType[]{
            CASTLE_LORDS_COURTYARD, CASTLE_LORDS_FLANK, CASTLE_LORDS_FRONT,
            CASTLE_LORDS_MELEE, CASTLE_LORDS_RANGED, CASTLE_LORDS_WALL,
            RANGED_STRENGTH, MELEE_STRENGTH, COURTYARD_STRENGTH, FLANK_SPACE, FRONT_SPACE, WALL_SPACE,
            COMBAT_STRENGTH
    };
    public static final ModType[] OVERCAPPED_MOD_TYPES = new ModType[]{
            OVERCAPPED_CASTLE_LORDS_COURTYARD, OVERCAPPED_CASTLE_LORDS_FLANK, OVERCAPPED_CASTLE_LORDS_FRONT,
            OVERCAPPED_CASTLE_LORDS_MELEE, OVERCAPPED_CASTLE_LORDS_RANGED, OVERCAPPED_CASTLE_LORDS_WALL,
            OVERCAPPED_RANGED_STRENGTH, OVERCAPPED_MELEE_STRENGTH, OVERCAPPED_COURTYARD_STRENGTH, OVERCAPPED_FLANK_SPACE,
            OVERCAPPED_FRONT_SPACE, OVERCAPPED_WALL_SPACE, OVERCAPPED_COMBAT_STRENGTH
    };



}
