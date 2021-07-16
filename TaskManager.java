package pl.coderslab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {
        try {
            readTaskFromFile();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        showMenu();
    }

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
        int index2 = 0;
        while (scanData.hasNextLine()) {
            String row = scanData.nextLine();
            String[] rowArr = row.split(",");
            arrData = Arrays.copyOf(arrData, arrData.length+1);
            arrData[index1] = rowArr;
            index1++;
            }

            return arrData;
        }
        

    }





