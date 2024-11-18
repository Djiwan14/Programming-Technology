/*
* Name : Shokhrukh Nigmatillaev
* Neptun : APVAVZ
* Task : 1
* */
package Assignment1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("This is assignment 1 and task 1. Please insert filename to run the program!");

        Scanner scanner = new Scanner(System.in);
        RaceSimulator race = new RaceSimulator();

        boolean finish = false;
        while(!finish){
            try {
                race.read(scanner.nextLine());
                race.printWinnerCreature();
                finish = true;
            }
            catch(NumberFormatException e){
                System.out.println(race.RED + "Try different file please, this is empty!" + race.RESET);
            }
            catch(NegativeNumberException e){
                System.out.println(race.RED + "Animals amount cannot be negative, try another file please!" + race.RESET);
            }
            catch(InvalidWaterLevelException e){
                System.out.println(race.RED + "Your input is invalid, water level cannot be negative please try another input!" + race.RESET);
            }
            catch(NoSuchCreatureException e){
                System.out.println(race.RED + "Your input is invalid, there is no such creature as it is given in the " +
                        "file. Please try another input!" + race.RESET);
            }
            catch(NoSuchDayException e){
                System.out.println(race.RED + "Your input is invalid, there is no such day as it is given in the " +
                        "file. Please try another input!" + race.RESET);
            }
            catch(FileNotFoundException e){
                System.out.println(race.RED + "Your file is not found, please try another input!" + race.RESET);
            }
        }
        scanner.close();
    }
}