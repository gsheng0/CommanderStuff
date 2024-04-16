package org.commander.equipment.mod2;

public class Equipment implements Comparable<Equipment>{
    private String name;
    private ModGroup modGroup;
    public Equipment(String name){
        this.name = name;
        this.modGroup = new ModGroup();
    }
    public void add(ModGroup modGroup){
        this.modGroup.add(modGroup);

    }
    public double getModScore(){
        return modGroup.getModScore();
    }
    public double getWaste(){
        return modGroup.getWaste();
    }
    public String getName(){ return name; }
    public ModGroup getModGroup(){
        return modGroup;
    }
    public static Equipment parseEquipment(String string){
        Equipment output;
        string = string.toLowerCase();
        String[] chunks = string.split("\n");
        if(string.startsWith("helmet")){
            output = new Helmet(chunks[0]);
        } else if(string.startsWith("body armour")){
            output = new BodyArmour(chunks[0]);
        } else if(string.startsWith("weapon")){
            output = new Weapon(chunks[0]);
        } else if(string.startsWith("artifact")){
            output = new Artifact(chunks[0]);
        } else if(string.startsWith("gem")){
            output = new Gem(chunks[0]);
        } else{
            throw new RuntimeException("Error parsing equipment piece: " + chunks[0]);
        }
        for(int i = 1; i < chunks.length; i++){
            Mod mod = Mod.parseMod(chunks[i]);
            output.add(mod.toModGroup());
        }
        output.modGroup.setParseString(string);
        return output;
    }

    @Override
    public int compareTo(Equipment o) {
        return Double.compare(o.getModScore(), this.getModScore());
    }

}
