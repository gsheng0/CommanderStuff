package org.commander.equipment.mod2;

public class Mod implements Comparable<Mod> {
    private ModType modType;
    private final double globalValue;
    private final double conditionalValue;
    private double waste;
    private double modScore;
    public Mod(ModType modType, double globalValue, double conditionalValue){
        this.modType = modType;
        this.globalValue = globalValue;
        this.conditionalValue = conditionalValue;

        this.modScore = 0.0;
        if (modType.getConditionalMax() != 0.0) {
            modScore += conditionalValue/modType.getConditionalMax();
        }
        if(modType.getGlobalMax() != 0.0){
            modScore += globalValue/modType.getGlobalMax();
        }
//        if(modType.equals(ModType.COMBAT_STRENGTH)){
//            modScore /= 2;
//        }
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
    public Mod add(double globalValue, double conditionalValue){
        double waste = 0.0;
//        double beforeGlobalValue = this.globalValue;
//        double beforeConditionalValue = this.conditionalValue;
//        this.globalValue = Math.min(globalValue + this.globalValue, modType.getGlobalMax());
//        this.conditionalValue = Math.min(conditionalValue + this.conditionalValue, modType.getConditionalMax());
//        double changeInGlobalValue = this.globalValue - beforeGlobalValue;
//        double changeInConditionalValue = this.conditionalValue - beforeConditionalValue;
//        return globalValue - changeInGlobalValue + conditionalValue - changeInConditionalValue;
        return new Mod(modType,
                Math.min(globalValue + this.globalValue, modType.getGlobalMax()),
                Math.min(conditionalValue + this.conditionalValue, modType.getConditionalMax()));
    }

    @Override
    public int compareTo(Mod o) {
        return Double.compare(this.modScore, o.modScore);
    }
    public static Mod parseMod(String string){
        double value = Double.parseDouble(string.substring(0, string.indexOf(" ") - 1));
        ModType modType = ModType.parseModType(string.substring(string.indexOf(" ") + 1));
        return string.contains("castle lords") ?
                new Mod(modType, 0.0, value) :
                new Mod(modType, value, 0.0);
    }
    public double getTotal() { return globalValue + conditionalValue; }
    public Mod add(Mod other){
        if(!modType.equals(other.modType)){
            return this;
        }
        return new Mod(modType,
                Math.min(modType.getGlobalMax(), this.globalValue + other.globalValue),
                Math.min(modType.getConditionalMax(), this.conditionalValue + other.conditionalValue));
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(modType.toString());
        builder.append(": ");
        builder.append(this.getTotal());
        if(globalValue > 0){
            builder.append("\n\tglobal value: ");
            builder.append(globalValue);
        }
        if(conditionalValue > 0){
            builder.append("\n\tconditional value: ");
            builder.append(conditionalValue);
        }

        return builder.toString();
    }
}
