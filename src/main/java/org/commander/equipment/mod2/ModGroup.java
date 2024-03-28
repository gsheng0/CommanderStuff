package org.commander.equipment.mod2;

import java.util.HashMap;
import java.util.HashSet;

public class ModGroup {
    private HashMap<ModType, Mod> map;
    private HashSet<Mod> set;
    private double waste = 0.0;
    private double modScore = 0.0;
    public ModGroup(){
        map = new HashMap<>();
        set = new HashSet<>();
    }
    public ModGroup(Mod mod){
        map = new HashMap<>();
        set = new HashSet<>();
        set.add(mod);
        map.put(mod.getModType(), mod);
    }
    public void add(Mod mod){
        set.add(mod);
        if(!map.containsKey(mod.getModType())){
            map.put(mod.getModType(), mod);
        } else{
            Mod mapMod = map.get(mod.getModType());
            waste += mapMod.add(mod.getGlobalValue(), mod.getConditionalValue());
        }
        calculateModScore();
    }
    public void add(ModGroup modGroup){
        for(ModType modType : modGroup.getMods().keySet()){
            Mod mod = modGroup.getMods().get(modType);
            add(mod);
        }
        calculateModScore();
    }
    public HashMap<ModType, Mod> getMods(){ return map; }
    public HashSet<Mod> getModSet(){ return set; }
    public double getWaste() { return waste; }
    private void calculateModScore(){
        modScore = 0.0;
        for(ModType modType : map.keySet()){
            modScore += map.get(modType).getModScore();
        }
    }
    public double getModScore(){
        return modScore;
    }

}
