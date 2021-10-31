import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.util.InputMismatchException;
import java.time.Duration;
import java.util.Arrays;
public class Clinic {
private File patientFile;
private int day;

public Clinic(File file) {
        patientFile = file;
}
public Clinic(String fileName) {
        this(new File(fileName));
}

int timeToHealInt;
Scanner fileScan = null;
Scanner input = new Scanner(System.in);
String tempTreatedPatientInfo = "";
String treatedPatientInfo = "";

public String nextDay(File f) throws FileNotFoundException, InputMismatchException, InvalidPetException {
        try {
                fileScan = new Scanner(f);
        }
        catch (FileNotFoundException fnfe) {

        }

        while (fileScan.hasNextLine()) {
                String tempLine = "";
                tempLine = fileScan.nextLine();
                String[] line = tempLine.split(",");
                System.out.println("Consultation for " + line[0] + " the " + line[1] +
                                   " at " + line[3] + ". \n" +"What is the health of " + line[0]);
                if (!line[1].equals("Cat") && !line[1].equals("Dog")) {
                        throw new InvalidPetException();
                }
                Pet pet;
                double healthLevel = 0;
                int painLevel = 0;
                boolean continueInput = true;
                do {
                        try {
                                healthLevel = input.nextDouble();
                                continueInput = false;
                        }
                        catch (InputMismatchException ex) {
                                System.out.println("Invalid input. Please input a valid double.");
                                input.nextLine();
                        }
                } while(continueInput);

                continueInput = true;
                input.nextLine();
                System.out.println("On a scale of 1 to 10, how much pain is " + line[0] + " in right now?");

                do {
                        try {
                                painLevel = input.nextInt();
                                continueInput = false;
                        }
                        catch (InputMismatchException ex) {
                                System.out.println("Invalid input. Please input a valid int.");
                                input.nextLine();
                        }
                } while(continueInput);

                int miceCaught;
                double droolRate;
                if (line[1].equals("Cat")) {
                        pet = new Cat(line[0], healthLevel, painLevel, miceCaught = Integer.parseInt(line[2]));
                }
                else {
                        pet = new Dog(line[0], healthLevel, painLevel, droolRate = Double.parseDouble(line[2]));
                }
                pet.speak();
                int timeToHealInt = pet.treat();
                int timeIn = Integer.parseInt(line[3]);
                timeToHealInt = ((timeToHealInt/60)*100)+(timeToHealInt%60);
                int timeOut = timeIn + timeToHealInt;
                tempTreatedPatientInfo = pet.getName()+","+line[1]+","+line[2]+","+"Day "+day+","+timeIn+","+timeOut+","+healthLevel+","+painLevel+"\n";
                treatedPatientInfo = treatedPatientInfo + tempTreatedPatientInfo;
        }
        day = day +1;
        return treatedPatientInfo;
}
public String nextDay(String fileName) throws FileNotFoundException, InputMismatchException, InvalidPetException {
        return nextDay(new File(fileName));
}

int dayNumberAsInt;
public boolean addToFile(String patientInfo) throws FileNotFoundException {
        String[] arrayOfPatientInfo = patientInfo.split(",");
        System.out.println(arrayOfPatientInfo[3]);
        arrayOfPatientInfo[3] = "Day 1";
        System.out.println(arrayOfPatientInfo[3]);
        boolean noError = true;
        try {
                fileScan = new Scanner(patientFile);
        }
        catch (FileNotFoundException fnfe) {

        }
        String tempLine = "";
        String tempLongString = "";
        while (fileScan.hasNextLine()) {
                tempLine = fileScan.nextLine();
                tempLongString = tempLongString + tempLine + "\n";
        }
        if (fileScan != null) {
                fileScan.close();
        }
        String[] tempArray = tempLongString.split("\\n");
        int size = tempArray.length;
        boolean hasStartDate = false;
        String dayNumberAsString = "";
        if (tempArray[size - 1].startsWith("Day ")) {
                hasStartDate = true;
                String[] lastWord = tempArray[size-1].split(" ");
                dayNumberAsInt = Integer.parseInt(lastWord[1]) +1;
                dayNumberAsString = String.valueOf(dayNumberAsInt);
                arrayOfPatientInfo[3] = "Day " + dayNumberAsInt;
                if (lastWord[1].equals("0")) {
                        arrayOfPatientInfo[3] = "Day 2";
                        dayNumberAsInt = 2;
                }
        }
        dayNumberAsInt = dayNumberAsInt -1;
        String[][] doubleTempArray = new String[size][];
        int counter = 0;
        boolean oldPatient = false;
        for (String line : tempArray) {
                doubleTempArray[counter] = line.split(",");
                if (doubleTempArray[counter][0].equals(arrayOfPatientInfo[0])) {
                        tempArray[counter]=tempArray[counter]+ ","+arrayOfPatientInfo[3]+","+arrayOfPatientInfo[4]+","+arrayOfPatientInfo[5]+","+arrayOfPatientInfo[6]+","+arrayOfPatientInfo[7];
                        oldPatient = true;
                }
                counter++;
        }
        File fileOut = new File("Patients.csv");
        PrintWriter filePrint = null;
        try{
                filePrint = new PrintWriter(fileOut);
                int y = 0;

                for (y = 0; y <= tempArray.length - 2; y++) {
                        filePrint.println(tempArray[y]);
                }
                if (!oldPatient) {
                        filePrint.println(arrayOfPatientInfo[0]+","+arrayOfPatientInfo[1]+","+arrayOfPatientInfo[2]+","+arrayOfPatientInfo[3]+","+arrayOfPatientInfo[4]+","+arrayOfPatientInfo[5]+","+arrayOfPatientInfo[6]+","+arrayOfPatientInfo[7]);
                }
                if (!hasStartDate) {
                        filePrint.println(tempArray[tempArray.length-1]);
                        filePrint.println("Day 0");
                }
                else if (hasStartDate) {
                        filePrint.println("Day " + dayNumberAsInt);
                }
        }
        catch (FileNotFoundException e) {
                noError = false;
                System.out.println(e.getMessage());
        }
        finally {
                if (filePrint != null) {
                        filePrint.close();
                }
        }
        return noError;
}
}
