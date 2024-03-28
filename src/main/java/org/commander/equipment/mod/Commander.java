package org.commander.equipment.mod;

import org.commander.utils.Temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Commander {
    private Helmet helmet;
    private BodyArmour bodyArmour;
    private Artifact artifact;
    private Weapon weapon;
    private List<Gem> gems;
    private HashMap<String, Double> stats;
    public Commander(Helmet helmet, Weapon weapon, BodyArmour bodyArmour, Artifact artifact, List<Gem> gems){
        this.helmet = helmet;
        this.weapon = weapon;
        this.bodyArmour = bodyArmour;
        this.artifact = artifact;
        this.gems = gems;
    }
    public HashMap<String, Double> getStats(){
        if(stats != null){
            return stats;
        }
        List<Equipment> equipmentList = new ArrayList<>();
        equipmentList.add(helmet);
        equipmentList.add(weapon);
        equipmentList.add(bodyArmour);
        equipmentList.add(artifact);
        equipmentList.addAll(gems);
        stats = Temp.calculateStats(equipmentList);
        return stats;
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(stats.toString());
        builder.append("\n");
        builder.append(helmet.toString());
        builder.append("\n");
        builder.append(weapon.toString());
        builder.append("\n");
        builder.append(bodyArmour.toString());
        builder.append("\n");
        builder.append(artifact.toString());
        builder.append("\n");
        for(Gem gem : gems){
            builder.append(gem.toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}
