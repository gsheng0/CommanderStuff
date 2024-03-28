package org.commander;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Something {

    public static void main(String[] args) {
        String inputFile = "CastEquipment.txt"; // Name of the input file
        String outputFile = "CastEquipmentLabelled.txt"; // Name of the output file

        try {
            addEquipmentNumbers(inputFile, outputFile);
            System.out.println("Equipment numbering completed successfully!");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void addEquipmentNumbers(String inputFile, String outputFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        int weaponCount = 0;
        int bodyArmourCount = 0;
        int helmetCount = 0;
        int gemCount = 0;
        int artifactCount = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Weapon")) {
                weaponCount++;
                line = line.concat(" " + weaponCount);
            } else if (line.startsWith("Body Armour")) {
                bodyArmourCount++;
                line = line.concat(" " + bodyArmourCount);
            } else if (line.startsWith("Helmet")) {
                helmetCount++;
                line = line.concat(" " + helmetCount);
            } else if (line.startsWith("Gem")) {
                gemCount++;
                line = line.concat(" " + gemCount);
            } else if (line.startsWith("Artifact")) {
                artifactCount++;
                line = line.concat(" " + artifactCount);
            }
            writer.write(line);
            writer.newLine();
        }

        reader.close();
        writer.close();
    }
}

