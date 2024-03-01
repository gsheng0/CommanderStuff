package org.commander.equipment;

import org.commander.equipment.mod.Mod;

import java.util.ArrayList;
import java.util.List;

public class Equipment {
    private List<Mod> mods;
    public Equipment(List<Mod> mods){
        this.mods = mods;
    }
    public List<Mod> getMods() { return mods; }
    public static Equipment parseEquipment(String string){
        String[] lines = string.split("\n");
        List<Mod> mods = new ArrayList<>();
        for(int i = 1; i < lines.length; i++){
            mods.add(Mod.parseMod(lines[i]));
        }
        lines[0] = lines[0].toLowerCase();
        if(lines[0].contains("helmet")){
            return new Helmet(mods);
        } else if(lines[0].contains("body armour")){
            return new BodyArmour(mods);
        } else if(lines[0].contains("artifact")){
            return new Artifact(mods);
        } else if(lines[0].contains("weapon")){
            return new Weapon(mods);
        } else if(lines[0].contains("gem")){
            return new Gem(mods);
        }
        return null;
    }
}
