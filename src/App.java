/*
 * Created on 2025-10-03
 *
 * Copyright (c) 2025 Nadine von Frankenberg
 */

/*** Simplified for LAB06 ***/
import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileNotFoundException;

public class App {
    
    public static void main(String[] args) {
        if (args.length == 0){
            System.out.println("Please provide a filename as an argument.");
            return; // if no filename exit the program
        }

        System.out.println("File to read: " + args[0]); 
        Cat[] cats = readCatsFromFile(args[0]);

        if (cats != null){ // if array not null
            for (Cat cat : cats){ // enhanced for loop iterates through the entire array
                printCatInfo(cat); // prints cat info
            }
        }
        else{
            System.out.println("The array is null!");
        }
    }

    public static Cat[] readCatsFromFile(String fileName) {
        System.out.println("Reading data from file: " + fileName);
        Cat[] cats = null; // i know there's 5 cats in the csv file but slide 16 on the lab says to read "multiple stories" so i'm assuming i need to make it the code adjust to various amounts of cats
        int count = 0;

        File file = new File(fileName);

        try (Scanner myScanner = new Scanner(file)){ // scanner reads file
            if (myScanner.hasNextLine()){ // returns true if there is another line in the scanner
                myScanner.nextLine(); // Owner, Cat, Age, Sound, Story is skipped
            }
            while (myScanner.hasNextLine()){ // ends when there is no more lines in the file read by the scanner
                myScanner.nextLine();
                count++; // counts cats
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found: " + fileName);
            return null; // no cats found
        }

         cats = new Cat[count]; // now know how many cats are in the csv file
         int track = 0; // track number of cats being added into array

          try (Scanner myScanner = new Scanner(file)){
            if (myScanner.hasNextLine()){
                myScanner.nextLine();
            }

            while(myScanner.hasNextLine()){
                String line = myScanner.nextLine();
                String[] data = line.split(",\\s*");
                Cat cat = createCatFromFileData(data); // creates new cat object based on data read and gets stored in the cat array
                if (track < cats.length && cat != null){ // as long as tracking number of cats isn't bigger than total amount of cats & cat info isn't null
                    cats[track] = cat; // array of cats at index track gets filled with info of cat 
                    track++; // move onto next cat
                }
            }
        }
            catch (FileNotFoundException e){
                System.out.println("File was not found: " + fileName);
                return null;
            }
        return cats; // returns array that is now full of cat info
    }

    private static Cat createCatFromFileData(String[] data) {
        String ownerName = readStringSafely(data, 0, "Unknown owner name");
        String name = readStringSafely(data, 1, "Unknown cat name");
        int age = parseIntSafely(data, 2, -1);
        String sound = readStringSafely(data, 3, "Unknown sound");
        String story = readStringSafely(data, 4, "Unknown story");

        Owner owner = new Owner(ownerName);

        return new Cat(name, sound, age, story, owner);
    }

    /*
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * ! No need to touch the methods below !
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    // Helper to safely parse string
    private static String readStringSafely(String[] data, int index, String defaultValue) {
        return (data != null && index < data.length && data[index] != null && !data[index].isEmpty())
                ? data[index].trim()
                : defaultValue;
    }

    // Helper to safely parse int
    private static int parseIntSafely(String[] data, int index, int defaultValue) {
        if (data == null || index >= data.length)
            return defaultValue;
        try {
            return Integer.parseInt(data[index].trim());
        } catch (NumberFormatException e) {
            System.out.println("'" + data[index] + "' cannot be interpreted as an int!");
            return defaultValue;
        }
    }

    public static void printCatInfo(Cat cat) {
        System.out.println();
        if (cat != null && cat.getOwner() != null) {
            System.out.print(cat.getOwner().getName() + " has adopted " + cat.getName());
            if (cat.getAge() != 404) {
                System.out.print(" (" + cat.getAge() + ")");
            }
            if (!(cat.getStory().equals("Unknown story"))) {
                System.out.println(" and shared this story: ");
                System.out.println(cat.getStory().toString());
            } else {
                System.out.println(".");
            }
        }
    }
}