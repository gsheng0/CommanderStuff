package org.example.equipment;

import org.example.utils.Temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Commander {
    private Helmet helmet;
    private BodyArmour bodyArmour;
    private Artifact artifact;
    private Weapon weapon;
    private List<Gem> gems;
    public Commander(Helmet helmet, Weapon weapon, BodyArmour bodyArmour, Artifact artifact, List<Gem> gems){
        this.helmet = helmet;
        this.weapon = weapon;
        this.bodyArmour = bodyArmour;
        this.artifact = artifact;
        this.gems = gems;
    }
    public HashMap<String, Integer> getStats(){
        List<Equipment> equipmentList = new ArrayList<>();
        equipmentList.add(helmet);
        equipmentList.add(weapon);
        equipmentList.add(bodyArmour);
        equipmentList.add(artifact);
        equipmentList.addAll(gems);
        return Temp.calculateStats(equipmentList);
    }
}
