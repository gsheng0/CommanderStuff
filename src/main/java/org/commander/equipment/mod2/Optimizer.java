package org.commander.equipment.mod2;

import org.commander.utils.FileHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Optimizer {
    private String outputFilename;
    private Armory armory;
    private List<EquipmentGroup> commanders;
    private String mostRecentRunConfig;
    private HashMap<ModType, Double> requirements;
    public Optimizer(Armory armory, String outputFilename){
        this.armory = armory;
        this.outputFilename = outputFilename;
        requirements = new HashMap<>();
    }
    public void optimize(){
        optimize(1.0, 1.0, 1.0, 1.0, 1.0, 8.05);
    }
    public void addRequirement(ModType modType, double requirement){
        if(requirements.containsKey(modType)){
            requirements.replace(modType, Math.max(requirement, requirements.get(modType)));
        }
        else{
            requirements.put(modType, requirement);
        }
    }
    private boolean meetsRequirements(EquipmentGroup group){
        HashMap<ModType, Mod> modTotals = group.getModTotals();
        for(ModType modType : requirements.keySet()){
            Mod mod  = modTotals.get(modType);
            if(mod == null || mod.getGlobalValue() + mod.getConditionalValue() < requirements.get(modType)){
                return false;
            }
        }
        return true;
    }
    public void optimize(double helmetsCoefficient, double bodyArmoursCoefficient, double weaponsCoefficient, double artifactsCoefficient, double gemCombinationsCoefficient, double minModScore){
        double max = 0.0;
        StringBuilder builder = new StringBuilder(armory.getFilename());
        builder.append(" first x% search \n\tHelmets: ");
        builder.append(helmetsCoefficient);
        builder.append("\n\tBody Armours: ");
        builder.append(bodyArmoursCoefficient);
        builder.append("\n\tWeapons: ");
        builder.append(weaponsCoefficient);
        builder.append("\n\tArtifacts: ");
        builder.append(artifactsCoefficient);
        builder.append("\n\tGem Combinations: ");
        builder.append(gemCombinationsCoefficient);
        mostRecentRunConfig = builder.toString();
        this.commanders = new ArrayList<>();
        List<Helmet> helmets = armory.getHelmets();
        List<BodyArmour> bodyArmours = armory.getBodyArmours();
        List<Weapon> weapons = armory.getWeapons();
        List<Artifact> artifacts = armory.getArtifacts();
        List<EquipmentGroup> gemCombinations = armory.getGemCombinations();
        for(int ba = 0; ba < bodyArmoursCoefficient * bodyArmours.size(); ba++){
            for(int w = 0; w < weaponsCoefficient * weapons.size(); w++){
                for(int h = 0; h < helmetsCoefficient * helmets.size(); h++){
                    for(int a = 0; a < artifactsCoefficient * artifacts.size(); a++){
                        for(int g = 0; g < gemCombinationsCoefficient * gemCombinations.size(); g++){
                            EquipmentGroup commander = new EquipmentGroup();
                            commander.add(bodyArmours.get(ba));
                            commander.add(weapons.get(w));
                            commander.add(helmets.get(h));
                            commander.add(artifacts.get(a));
                            commander.add(gemCombinations.get(g));
                            if(commander.getModScore() > minModScore){
                                if(meetsRequirements(commander)){
                                    commanders.add(commander);
                                }

                            }
                            max = Math.max(max, commander.getModScore());
                        }
                    }
                }
            }
        }
        System.out.println("max score; " + max);
        Collections.sort(commanders);
    }
    public void optimize(int numHelmets, int numBodyArmours, int numWeapons, int numArtifacts, int numGemCombinations){
        StringBuilder builder = new StringBuilder(armory.getFilename());
        builder.append(" first x search \n\tHelmets: ");
        builder.append(numHelmets);
        builder.append("\n\tBody Armours: ");
        builder.append(numBodyArmours);
        builder.append("\n\tWeapons: ");
        builder.append(numWeapons);
        builder.append("\n\tArtifacts: ");
        builder.append(numArtifacts);
        builder.append("\n\tGem Combinations: ");
        builder.append(numGemCombinations);
        mostRecentRunConfig = builder.toString();

        this.commanders = new ArrayList<>();
        List<Helmet> helmets = armory.getHelmets();
        List<BodyArmour> bodyArmours = armory.getBodyArmours();
        List<Weapon> weapons = armory.getWeapons();
        List<Artifact> artifacts = armory.getArtifacts();
        List<EquipmentGroup> gemCombinations = armory.getGemCombinations();
        for(int ba = 0; ba < numBodyArmours; ba++){
            for(int w = 0; w < numWeapons; w++){
                for(int h = 0; h < numHelmets; h++){
                    for(int a = 0; a < numArtifacts; a++){
                        for(int g = 0; g < numGemCombinations; g++){
                            EquipmentGroup commander = new EquipmentGroup();
                            commander.add(bodyArmours.get(ba));
                            commander.add(weapons.get(w));
                            commander.add(helmets.get(h));
                            commander.add(artifacts.get(a));
                            commander.add(gemCombinations.get(g));
                            commanders.add(commander);

                        }
                    }
                }
            }
        }
        Collections.sort(commanders);
    }
    public void writeResults(int num){
        if(mostRecentRunConfig != null){
            FileHandler.write(outputFilename, mostRecentRunConfig);
        }

        FileHandler.write(outputFilename, "\n");
        System.out.println("Made " + commanders.size() + " commanders");
        for(int i = 0; i < Math.min(num, commanders.size()); i++){
            FileHandler.write(outputFilename, commanders.get(i).toString());
            FileHandler.write(outputFilename, "\n");
        }
        FileHandler.write(outputFilename, "\n");
    }
    public List<EquipmentGroup> getCommanders() { return commanders; }

    
}
