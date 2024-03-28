package org.commander.equipment.mod2;

public class Mod implements Comparable<Mod> {
    private ModType modType;
    private double globalValue;
    private double conditionalValue;
    private double modScore;
    public Mod(ModType modType, double globalValue, double conditionalValue){
        this.modType = modType;
        this.globalValue = globalValue;
        this.conditionalValue = conditionalValue;
        this.modScore = conditionalValue/modType.getConditionalMax() + globalValue/modType.getGlobalMax();
    }
    public double getModScore(){
        return modScore;
    }
    public ModType getModType(){
        return modType;
    }
    public double getGlobalValue() { return globalValue; }
    public double getConditionalValue() { return conditionalValue; }
    public ModGroup toModGroup(){
        return new ModGroup(this);
    }
    /**
     *
     * @param globalValue
     * @param conditionalValue
     * @return the sum of the overflow of stats (the normalized amount of the stat that goes over the cap)
     */
    public double add(double globalValue, double conditionalValue){
        double waste = 0.0;
        double beforeGlobalValue = this.globalValue;
        double beforeConditionalValue = this.conditionalValue;
        this.globalValue = Math.min(globalValue + this.globalValue, modType.getGlobalMax());
        this.conditionalValue = Math.min(conditionalValue + this.conditionalValue, modType.getConditionalMax());
        double changeInGlobalValue = this.globalValue - beforeGlobalValue;
        double changeInConditionalValue = this.conditionalValue - beforeConditionalValue;
        return globalValue - changeInGlobalValue + conditionalValue - changeInConditionalValue;
    }

    @Override
    public int compareTo(Mod o) {
        return Double.compare(this.modScore, o.modScore);
    }
    public static Mod parseMod(String string){
        double value = Double.parseDouble(string.substring(0, string.indexOf(" ")));
        ModType modType = ModType.parseModType(string.substring(string.indexOf(" ") + 1));
        return string.contains("castle lords") ?
                new Mod(modType, 0.0, value) :
                new Mod(modType, value, 0.0);
    }
    public double getTotal() { return globalValue + conditionalValue; }
}
