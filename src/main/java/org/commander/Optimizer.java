package org.commander;

import org.commander.equipment.*;
import org.commander.equipment.mod.ModType;
import org.commander.utils.FileHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Optimizer {
    private String fileName;
    private List<Equipment> equipmentList;
    private List<Helmet> helmets;
    private List<BodyArmour> bodyArmours;
    private List<Weapon> weapons;
    private List<Artifact> artifacts;
    private List<Gem> gems;
    public Optimizer(String fileName){
        this.fileName = fileName;
        retrieveEquipmentSet();
        sortEquipment();
    }
    public List<Commander> optimize(int rangedMin, int meleeMin, int minCourtyard, int minFront, int minFlank) {
        List<List<Gem>> gemCombinations = getGemCombinations(gems);
        List<Commander> commanders = new ArrayList<>();
        for (Helmet helmet : helmets) {
            for (Weapon weapon : weapons) {
                for (BodyArmour bodyArmour : bodyArmours) {
                    for (Artifact artifact : artifacts) {
                        for (List<Gem> gemCombination : gemCombinations) {
                            Commander commander = new Commander(
                                    helmet, weapon, bodyArmour,
                                    artifact, gemCombination);
                            if(meetsRequirements(rangedMin, meleeMin, minCourtyard, minFront, minFlank,
                                    commander)){
                                commanders.add(commander);
                            }

                        }
                    }
                }
            }
        }
        return commanders;
    }
    private boolean meetsRequirements(int rangedMin, int meleeMin, int minCourtyard, int minFront, int minFlank, Commander commander) {
        HashMap<String, Double> statsMap = commander.getStats();
        if (rangedMin != 0 && (!statsMap.containsKey(ModType.RANGED_STRENGTH.getName()) || statsMap.get(ModType.RANGED_STRENGTH.getName()) < rangedMin)) {
            return false;
        }
        if (meleeMin != 0 && (!statsMap.containsKey(ModType.MELEE_STRENGTH.getName()) || statsMap.get(ModType.MELEE_STRENGTH.getName()) < meleeMin)) {
            return false;
        }
        if (minCourtyard != 0 && (!statsMap.containsKey(ModType.COURTYARD_STRENGTH.getName()) || statsMap.get(ModType.COURTYARD_STRENGTH.getName()) < minCourtyard)) {
            return false;
        }
        if (minFront != 0 && (!statsMap.containsKey(ModType.FRONT_SPACE.getName()) || statsMap.get(ModType.FRONT_SPACE.getName()) < minFront)) {
            return false;
        }
        if (minFlank != 0 && (!statsMap.containsKey(ModType.FLANK_SPACE.getName()) || statsMap.get(ModType.FLANK_SPACE.getName()) < minFlank)) {
            return false;
        }
        return true;
    }
    public List<List<Gem>> getGemCombinations(List<Gem> gems){
        List<List<Gem>> gemCombinations = new ArrayList<>();
        for(int a = 0; a < gems.size(); a++){
            for(int b = a + 1; b < gems.size(); b++){
                for(int c = b + 1; c < gems.size(); c++){
                    for(int d = c + 1; d < gems.size(); d++){
                        List<Gem> gemCombination = new ArrayList<>();
                        gemCombination.add(gems.get(a));
                        gemCombination.add(gems.get(b));
                        gemCombination.add(gems.get(c));
                        gemCombination.add(gems.get(d));
                        gemCombinations.add(gemCombination);
                    }
                }
            }
        }
        return gemCombinations;
    }
    public void retrieveEquipmentSet(){
        String fileContents = FileHandler.read("/Users/gsheng/IdeaProjects/CommanderStuff/src/main/resources/" + fileName);
        String[] strings = fileContents.split("\n\n");
        equipmentList = new ArrayList<>();
        for(String string : strings){
            equipmentList.add(Equipment.parseEquipment(string));
        }
    }
    public void sortEquipment(){
        helmets = new ArrayList<>();
        bodyArmours = new ArrayList<>();
        weapons = new ArrayList<>();
        artifacts = new ArrayList<>();
        gems = new ArrayList<>();
        for(Equipment equipment : equipmentList) {
            if (equipment instanceof Helmet helmet) {
                helmets.add(helmet);
            } else if (equipment instanceof BodyArmour bodyArmour) {
                bodyArmours.add(bodyArmour);
            } else if (equipment instanceof Weapon weapon) {
                weapons.add(weapon);
            } else if (equipment instanceof Artifact artifact) {
                artifacts.add(artifact);
            } else if (equipment instanceof Gem gem) {
                gems.add(gem);
            }
        }
    }
}
