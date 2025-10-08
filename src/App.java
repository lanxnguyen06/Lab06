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

        // TODO 1.0: Check the argument's length
            if (args.length == 0){
            System.out.println("Please provide a filename as an argument.");
            return; // if no filename exit the program
        }
        System.out.println("File to read: " + args[0]); 
        // TODO 1.1: Call readCatsFromFile()
        Cat[] cats = readCatsFromFile(args[0]);

        // TODO 2.3: Check if array is not null,
        if (cats != null){
            for (Cat cat : cats){ // iterates through the entire array
                printCatInfo(cat); // prints cat info
            }
        }
        else{
            System.out.println("The array is null!");
        }
    }

    // TODO 1.1: Implement
    public static Cat readCatsFromFile(String fileName) {
        System.out.println("Reading data from file: " + fileName);
        // TODO: Declare an array of cats

        // Try-catch block around reading the csv file
        try (Scanner myScanner = new Scanner(new File(fileName))) {
            // TODO : Skip header line
           
            // TODO: read each line of the file
            // Hint: you can use line.split(",\\s*"); 
            // to split a string by a delimiter (String[])
            
            // TODO: use createCatFromFileData() to create a new cat object from the data read
            // then store it in the cat array

        } 
        return null;
    }

    // TODO: Read cat object from file (decide on whether you need a parameter!)
    private static Cat createCatFromFileData() {
        // Hint: use the helper methods below
        // parseIntSafely & readStringSafely expect an array, index of the respective
        // value to be parsed, and a default value
        String ownerName = null;
        Owner owner = null;
        String name = null;
        int age = -1;
        String sound = null;
        String story = null;

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