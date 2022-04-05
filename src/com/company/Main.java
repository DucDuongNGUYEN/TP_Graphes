package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private final static String FILE_URL = "C:\\LICENCE 2\\S4\\Algorithmique 1\\graphe.txt";
    public static void main(String[] args) throws FileNotFoundException {
        displayData();
    }
    public static void displayData() throws FileNotFoundException {
        /*
        try {
            File file = new File(FILE_URL);
            InputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line ;
            int n = Integer.parseInt(reader.readLine());
            System.out.println("nombre de sommets : " + n);
            ArrayList<Integer> origine = new ArrayList<>();
            ArrayList<Integer> extreme = new ArrayList<>();
            ArrayList<Integer> longer = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] result = line.split(" ");
                origine.add(Integer.parseInt(result[0]));
                extreme.add(Integer.parseInt(result[1]));
                longer.add(Integer.parseInt(result[2]));
            }
            System.out.println(origine);
            System.out.println(extreme);
            System.out.println(longer);

        } catch (IOException e) {
            e.printStackTrace();
        }
         */
        FileInputStream fileInputStream = new FileInputStream(FILE_URL);
        Scanner scanner = new Scanner(fileInputStream);
        int n = Integer.parseInt(scanner.nextLine());
        System.out.println("nombre de sommets : " + n);
        ArrayList<Integer> origine = new ArrayList<>();
        ArrayList<Integer> extreme = new ArrayList<>();
        ArrayList<Integer> longer = new ArrayList<>();
        try {
            while (scanner.hasNextLine()) {
                String[] result = scanner.nextLine().split(" ");
                origine.add(Integer.parseInt(result[0]));
                extreme.add(Integer.parseInt(result[1]));
                longer.add(Integer.parseInt(result[2]));
            }
            System.out.println(origine);
            System.out.println(extreme);
            System.out.println(longer);
        } finally {
            try {
                scanner.close();
                fileInputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}