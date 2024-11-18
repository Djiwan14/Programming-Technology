/*
 * Name : Shokhrukh Nigmatillaev
 * Neptun : APVAVZ
 * Task : 1
 * */
package Assignment1;

import java.io.*;
import java.util.*;

public class RaceSimulator {
    private ArrayList<Creature> creatures;

    // Colors for printing and differentiation errors and output |
    public final String YELLOW = "\u001B[33m";
    public final String BLUE = "\u001B[34m";
    public final String RED = "\u001B[31m";
    public final String RESET = "\u001B[0m";

    public RaceSimulator() {
        creatures = new ArrayList<>();
    }
    public void read(String fileName) throws IOException, NegativeNumberException,
            NoSuchCreatureException, NoSuchDayException, InvalidWaterLevelException {
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        // number of creatures are stored to line |
        String line = fileReader.readLine();
        int creaturesNumber = Integer.parseInt(line);
        // if amount of creatures is below 0 o 0 we throw exception |
        if (creaturesNumber < 0) {
            throw new NegativeNumberException();
        }
        // we read from file separating data one from each other |
        for (int i = 0; i < creaturesNumber; ++i) {
            String[] separators = new String[]{" ", "\t"};
            Creature creature = null;

            line = fileReader.readLine();
            if (line != null) {
                String[] parts = line.split("\\s+");
                String creatureName = parts[0];
                String letter = parts[1];
                int waterLevel = Integer.parseInt(parts[2]);

                // if water level is negative from the beginning we throw an error |
                CheckIfWaterLevelOfCreatureIsNegative(waterLevel);
                // checking which kind of creature is given |
                switch (letter) {
                    case "r":
                        creature = new Sandrunner(creatureName, waterLevel);
                        break;
                    case "w":
                        creature = new Walker(creatureName, waterLevel);
                        break;
                    case "s":
                        creature = new Sponge(creatureName, waterLevel);
                        break;
                    default:
                        throw new NoSuchCreatureException();
                }
            }
            creatures.add(creature);
        }
        line = fileReader.readLine();
        String days = line;
        // checking the type of the day |
        for (int j = 0; j < days.length(); ++j) {
            char c = days.charAt(j);
            switch (c) {
                case 's':
                    for (Creature creature : creatures) {
                        if (creature.isAlive()) {
                            creature.sunnyDay();
                        }
                    }
                    break;
                case 'r':
                    for (Creature creature : creatures) {
                        if (creature.isAlive()) {
                            creature.rainyDay();
                        }
                    }
                    break;
                case 'c':
                    for (Creature creature : creatures) {
                        if (creature.isAlive()) {
                            creature.cloudyDay();
                        }
                    }
                    break;
                default:
                    throw new NoSuchDayException();
            }
            for (Creature creature : creatures) {
                if (creature.isAlive())
                    System.out.println(YELLOW + "Our " + creature.getName() + " has " + creature.getDistance() + " moved distance!");
                else {
                    System.out.println(RED + "Our " + creature.getName() + " is dead" + RESET);
                }
            }
        }
    }
    // using max search for winner finding |
    public void printWinnerCreature(){
        int maxDistance = 0;
        String winnerName = "";
        for (Creature creature : creatures){
            if(creature.isAlive()){
                if(creature.getDistance() > maxDistance){
                    maxDistance = creature.getDistance();
                    winnerName = creature.getName();
                }
            }
            else{
                System.out.println(BLUE + creature.getName() + " is dead and cannot move further ((. " + RED);
            }
        }
        System.out.println(YELLOW + "Our winner is " + winnerName + " with the moved distance equal to " + maxDistance + RESET);
    }
    public void CheckIfWaterLevelOfCreatureIsNegative(int waterLevel) throws InvalidWaterLevelException {
        if (waterLevel < 0)
        {
            throw new InvalidWaterLevelException();
        }
    }
}
