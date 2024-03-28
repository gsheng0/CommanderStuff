package org.commander;

import org.commander.equipment.mod.*;
import org.commander.utils.FileHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class OptimizedOptimizer {
    private String inputFileName;
    private String outputFileName;
    private List<Equipment> equipmentList;
    private List<Helmet> helmets;
    private List<BodyArmour> bodyArmours;
    private List<Weapon> weapons;
    private List<Artifact> artifacts;
    private List<Gem> gems;
    public OptimizedOptimizer(String inputFileName, String outputFileName){
        this.outputFileName = outputFileName;
        this.inputFileName = inputFileName;
        retrieveEquipmentSet();
        sortEquipment();
    }
    public List<Commander> optimize(int rangedMin, int meleeMin, int minCourtyard, int minFront, int minFlank) {
        List<List<Gem>> gemCombinations = getGemCombinations(gems);
        System.out.println("Got Gem combinations");
        List<Commander> commanders = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger();

        helmets.parallelStream().forEach(helmet -> {
            weapons.parallelStream().forEach(weapon -> {
                bodyArmours.parallelStream().forEach(bodyArmour -> {
                    artifacts.parallelStream().forEach(artifact -> {
                        gemCombinations.parallelStream().forEach(gemCombination -> {
                            Commander commander = new Commander(
                                    helmet, weapon, bodyArmour,
                                    artifact, gemCombination);
                            if (meetsRequirements(rangedMin, meleeMin, minCourtyard, minFront, minFlank,
                                    commander)) {
                                commanders.add(commander);
                            }
                        });
                    });
                });
            });
            System.out.println("Processed helmets: " + counter.incrementAndGet());
        });

        System.out.println("Made " + commanders.size() + " commanders");
        commanders.parallelStream().forEach(commander ->
                FileHandler.write(outputFileName, commander.toString()));

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
    private boolean meetsRequirements(int rangedMin, int meleeMin, int minCourtyard, int minWallSpace, Commander commander) {
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
        if (minWallSpace != 0 && (!statsMap.containsKey(ModType.WALL_SPACE.getName()) || statsMap.get(ModType.WALL_SPACE.getName()) < minWallSpace)) {
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
        String fileContents = FileHandler.read("/Users/gsheng/IdeaProjects/CommanderStuff/" + inputFileName);
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
                if(gem.getMods().size() > 2){
                    gems.add(gem);
                }
            }
        }
    }
}
