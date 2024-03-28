package org.commander.equipment.mod;

import org.commander.utils.Temp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Equipment {
    private List<Mod> mods;
    private int id;
    private double score = -1.0;
    public Equipment(List<Mod> mods, int id){
        this.mods = mods;
        this.id = id;
    }
    public int getId() { return id; }
    public List<Mod> getMods() { return mods; }
    public static Equipment parseEquipment(String string){
        String[] lines = string.split("\n");
        List<Mod> mods = new ArrayList<>();
        for(int i = 1; i < lines.length; i++){
            mods.add(Mod.parseMod(lines[i]));
        }
        lines[0] = lines[0].toLowerCase();
        int id = Integer.parseInt(lines[0].split(" ")[lines[0].split(" ").length - 1]);

        if(lines[0].contains("helmet")){
            return new Helmet(mods, id);
        } else if(lines[0].contains("body armour")){
            return new BodyArmour(mods, id);
        } else if(lines[0].contains("artifact")){
            return new Artifact(mods, id);
        } else if(lines[0].contains("weapon")){
            return new Weapon(mods, id);
        } else if(lines[0].contains("gem")){
            return new Gem(mods, id);
        }
        return null;
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        if(this instanceof Helmet){
            builder.append("Helmet " + id + "\n");
        } else if(this instanceof BodyArmour){
            builder.append("Body Armour " + id + "\n");
        } else if(this instanceof Weapon){
            builder.append("Weapon " + id + "\n");
        } else if(this instanceof Artifact){
            builder.append("Artifact " + id + "\n");
        } else if(this instanceof Gem){
            builder.append("Gem " + id + "\n");
        }
        for(Mod mod: mods){
            builder.append(mod.toString());
            builder.append("\n");
        }
        return builder.toString();
    }
    public double calculateScore() {
        if(score != -1.0){
            return score;
        }
        HashMap<String, Double> stats = Temp.calculateModTypeTotals(Collections.singletonList(this));
        score = 0.0;
        for(String modType : stats.keySet()){
            score += stats.get(modType)/Mod.parseMod(modType).getModType().getMax();
        }

        return score;
    }
}
