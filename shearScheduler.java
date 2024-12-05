/*
WRITTEN BY JONATHAN GRIMES 
THIS PROGRAM UPLOADS A FILE OF SHEEPS AND THEN ORGANIZED BY MINHEAP*/

import java.util.Scanner;
import java.util.*;
import java.io.*;

public class shearScheduler {

    public static final String DELIM = "\t";
    public static final int BODY_FIELD_AMT = 3;

    public static void main(String[] args) {

        System.out.println();
        System.out.println(
                "-----------------------------------------\nHello! Welcome to the Sheep Shear Scheduler Program!\n-----------------------------------------");

        boolean runAgain;
        do {
            runAgain = createSchedule();
        } while (runAgain);
        System.out.println(
                "-----------------------------------------\nThank you for using the program! Good bye!\n-----------------------------------------");

    }

    // read Sheep file method
    public static sheep[] readSheepFile(String aName) {
        sheep[] tempArr = new sheep[200];
        try {
            int counter = 0;
            Scanner fileScanner = new Scanner(new File(aName));
            while (fileScanner.hasNextLine()) {
                String fileLine = fileScanner.nextLine();
                String[] splitLines = fileLine.split(DELIM);
                if (splitLines.length == BODY_FIELD_AMT) {
                    String name = splitLines[0];
                    String shearTime = splitLines[1];
                    String arrTime = splitLines[2];
                    sheep aSheep = new sheep(name, Integer.parseInt(shearTime), Integer.parseInt(arrTime));
                    tempArr[counter++] = aSheep;
                } else {
                    continue;
                }
            }
            fileScanner.close();
            sheep[] sheepArray = new sheep[counter];
            for (int i = 0; i < sheepArray.length; i++) {
                sheepArray[i] = tempArr[i];
            }
            return sheepArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempArr;
    }

    // create schedule
    public static boolean createSchedule() {
        boolean runProgram = true;
        System.out.println(
                "Enter a Sheeps-to-be-sheared File");
        Scanner keyboard = new Scanner(System.in);
        String fileName = keyboard.nextLine();

        // read the file when given user input
        sheep[] sheepArray = readSheepFile(fileName);
        sortArrival(sheepArray);

        // create heap of sheep. What a delightful thing to say outloud.
        MinHeap<sheep> sheepHeap = new MinHeap<>(sheepArray.length);
        int currentTime = 0;
        int sheepIndex = 0;

        System.out.println(
                "-----------------------------------------\nCreating schedule\n-----------------------------------------");

        // adding sheep to the heap
        while (sheepIndex < sheepArray.length || sheepHeap.peek() != null) {

            while (sheepIndex < sheepArray.length && sheepArray[sheepIndex].arrivalTime <= currentTime) {
                sheepHeap.add(sheepArray[sheepIndex]);
                sheepIndex++;
            }
            sheep nextSheep = sheepHeap.remove();
            if (nextSheep != null) {
                System.out.println("Name: " + nextSheep.name + ",\tShear Time: " + nextSheep.shearingTime
                        + ",\tArrival Time: " + nextSheep.arrivalTime);
                currentTime += nextSheep.shearingTime;
            } else {
                if (sheepIndex < sheepArray.length) {
                    currentTime = sheepArray[sheepIndex].arrivalTime;
                }
            }
        }

        // prompt to run the program again
        try {
            System.out.print(
                    "-----------------------------------------\nWould you like to run the program again? \"yes\" or \"no\"\n");
            Scanner keyboard2 = new Scanner(System.in);
            String response = keyboard2.nextLine();
            if (response.equals("yes"))
                return runProgram;
            if (response.equals("no")) {
                runProgram = false;
                return runProgram;
            }
        } catch (Exception e) {
            System.err.println("Please enter a valid input");
        }
        return runProgram;
    }

    // bubble sort by arrival time
    private static void sortArrival(sheep[] sheep) {
        for (int i = 0; i < sheep.length - 1; i++) {
            for (int j = 0; j < sheep.length - i - 1; j++) {
                if (sheep[j].arrivalTime > sheep[j + 1].arrivalTime) {
                    sheep temp = sheep[j];
                    sheep[j] = sheep[j + 1];
                    sheep[j + 1] = temp;
                }
            }
        }
    }

}
