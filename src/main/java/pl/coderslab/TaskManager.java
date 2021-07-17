package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {
        String[][] tab = null;
        try {
            tab = readTaskFromFile();
        } catch (IOException ex) {
            System.out.println(ex);
        }

        menu(tab);


    }

    public static String[][] menu(String[][] arrData) {
        showMenu();
        readChoseMenu(arrData);
        return arrData;
    }

    //Firs method which show every time without chose exit
    public static void showMenu() {
        System.out.print(ConsoleColors.BLUE);
        System.out.println("Please select an option");
        System.out.print(ConsoleColors.RESET);
        String[] showMenuVar = {"add", "remove", "list", "exit"};
        for (String s : showMenuVar) {
            System.out.println(s);
        }

    }

    public static String[][] readTaskFromFile() throws IOException {
        Path pathTasks = Paths.get("tasks.csv");
        Scanner scanData = new Scanner(pathTasks);
        String[][] arrData = new String[0][3];

        int index1 = 0;
        while (scanData.hasNextLine()) {
            String row = scanData.nextLine();
            String[] rowArr = row.split(", ");
            arrData = Arrays.copyOf(arrData, arrData.length + 1);
            arrData[index1] = rowArr;
            index1++;
        }

        return arrData;
    }

    public static String[][] readChoseMenu(String[][] arrData) {
        Scanner imputMenu = new Scanner(System.in);
        switch (imputMenu.nextLine()) {
            case "add":
                addTasks(arrData);
                break;
            case "remove":
                removeTask(arrData);
                break;
            case "list":
                listShow(arrData);
                break;

            case "exit":
                try {
                    exit(arrData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            default:
                System.out.println("Please select a correct option. ");
                menu(arrData);
        }
        return arrData;
    }


    public static String[][] addTasks(String[][] arrData) {
        Scanner inputTasks = new Scanner(System.in);
        System.out.println("add");
        System.out.println("Please add task description");
        String descTask = inputTasks.nextLine();
        System.out.println("Please add task due data");
        String dueData = inputTasks.nextLine();
        String impTask = null;
        while (true) {
            System.out.println("Is your task is important: true/false");
            impTask = inputTasks.nextLine();
            if (impTask.equals("true") || impTask.equals("false")) {
                break;
            }
        }
        String[] addedTask = {descTask, dueData, impTask};
        arrData = Arrays.copyOf(arrData, arrData.length + 1);
        arrData[arrData.length - 1] = addedTask;

        menu(arrData);
        return arrData;
    }

    public static void listShow(String[][] arrData) {
        System.out.println("list");
        int index = 0;
        for (String[] arrDatum : arrData) {
            System.out.print(index + " : ");
            index++;
            for (String s : arrDatum) {
                System.out.print(s + "  ");
            }
            System.out.print("\n");
        }
        menu(arrData);
    }

    public static String[][] removeTask(String[][] arrData) {
        Scanner scan = new Scanner(System.in);
        System.out.println("remove");
        System.out.println("Please select number to remove:");
        String numberString = scan.nextLine();
        int numIndex = 0;
        if (numberString.equals("")) {
            System.out.println("You don't write anything. Try again: \n");
        } else if (!NumberUtils.isParsable(numberString)) {
            System.out.println("Please write integer number: \n");
        } else {
            numIndex = Integer.parseInt(numberString);
            if (numIndex < 0) {
                System.out.println("There is no minus index. \n");
            }
            try {
                arrData = ArrayUtils.remove(arrData, numIndex);
                System.out.println("Remove " + numIndex + " done.");
            } catch (
                    IndexOutOfBoundsException e) {
                System.out.println("Index is out of bound");
            }
        }



        menu(arrData);
        return arrData;
    }

    public static void exit(String[][] arrData) throws IOException {


//        PrintWriter printWriter = new PrintWriter("tasks.csv");
        Path pathTasks = Paths.get("tasks.csv");

        System.out.println("exit");
        String rows = "";

        for (int i = 0; i < arrData.length; i++) {
            rows += String.join(", ", arrData[i]) + "\n";

        }

        Files.writeString(pathTasks, rows);
        System.out.print(ConsoleColors.RED + "Bye, bye");

    }


}


