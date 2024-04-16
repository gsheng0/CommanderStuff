package org.commander.equipment.mod2;

import org.commander.utils.FileHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Armory {
    private List<Equipment> equipmentSet;
    private List<Helmet> helmets;
    private List<BodyArmour> bodyArmours;
    private List<Weapon> weapons;
    private List<Artifact> artifacts;
    private List<Gem> gems;
    private List<EquipmentGroup> gemCombinations;
    private String filename;
    public static Armory load(String filename){
        return new Armory(filename);
    }
    private Armory(){}
    public String getFilename() { return filename; }
    private Armory(String filename){
        this.filename = filename;
        this.equipmentSet = new ArrayList<>();
        updateArmory();
    }
    public void updateArmory(){
        String fileContents = FileHandler.read("/Users/gsheng/IdeaProjects/CommanderStuff/" + filename);
        String[] strings = fileContents.split("\n\n");
        equipmentSet = new ArrayList<>();
        for(String string : strings){
            equipmentSet.add(Equipment.parseEquipment(string));
        }
        sortEquipment();
        calculateGemCombinations();
    }
    public void sortEquipment(){
        helmets = new ArrayList<>();
        bodyArmours = new ArrayList<>();
        weapons = new ArrayList<>();
        artifacts = new ArrayList<>();
        gems = new ArrayList<>();
        for(Equipment equipment : equipmentSet){
            if(equipment instanceof Helmet helmet){
                helmets.add(helmet);
            } else if(equipment instanceof BodyArmour bodyArmour){
                bodyArmours.add(bodyArmour);
            } else if(equipment instanceof Weapon weapon){
                weapons.add(weapon);
            } else if(equipment instanceof Artifact artifact){
                artifacts.add(artifact);
            } else if(equipment instanceof Gem gem){
                gems.add(gem);
            }
        }
        Collections.sort(helmets);
        Collections.sort(bodyArmours);
        Collections.sort(weapons);
        Collections.sort(artifacts);
        Collections.sort(gems);
    }
    public void calculateGemCombinations(){
        gemCombinations = new ArrayList<>();
        for(int a = 0; a < gems.size(); a++){
            for(int b = a + 1; b < gems.size(); b++){
                for(int c = b + 1; c < gems.size() * 0.5; c++){
                    for(int d = c + 1; d < gems.size() * 0.5; d++){
                        EquipmentGroup commander = new EquipmentGroup();
                        commander.add(gems.get(a));
                        commander.add(gems.get(b));
                        commander.add(gems.get(c));
                        commander.add(gems.get(d));
                        gemCombinations.add(commander);
                    }
                }
            }
        }
        Collections.sort(gemCombinations);
    }

    /**
     *
     * @return A list of helmets sorted in descending mod score
     */
    public List<Helmet> getHelmets() { return helmets; }

    /**
     *
     * @return A list of body armours sorted in descending mod score
     */
    public List<BodyArmour> getBodyArmours() { return bodyArmours; }

    /**
     *
     * @return A list of weapons sorted in descending mod score
     */
    public List<Weapon> getWeapons() { return weapons; }

    /**
     *
     * @return A list of artifacts sorted in descending mod score
     */
    public List<Artifact> getArtifacts() { return artifacts; }

    /**
     *
     * @return A list of gem combinatinos sorted in descending mod score
     */
    public List<EquipmentGroup> getGemCombinations() { return gemCombinations; }
}
