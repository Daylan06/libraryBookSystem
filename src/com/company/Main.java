package com.company;

import java.awt.print.Book;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class Main {

        private static ArrayList<String> books = new ArrayList<>();
        private static int menuInt = 0;
        private static int  booksToAdd = 1;



    private static File myObj = new File("NewFilename.txt"); //Change to something sensible
    private static Scanner scanner = new Scanner(System.in);


    public static void main (String[]args) {

        CreateFile();



        for (int j = 0; j < 4; j++) {
            ShowMenu();


        switch (menuInt) {

            case 1:
                int booksToAdd = Integer.parseInt(getInput("How many books do you wish to add?"));
                for (int i = 0; i < booksToAdd; i++) {
                    books.add(getBookDetails());
                }
                WriteToFile();
                break;

            case 2:
                ReadFile();
                break;

            case 3:
                DeleteFile();
                break;
        }

    }




    }

    public static void ShowMenu() {
        System.out.println("Please choose an option from the menu: ");
        System.out.println("1 - add books ");
        System.out.println("2 - display books ");
        System.out.println("3 - delete file ");

        Scanner input = new Scanner(System.in);
       menuInt = input.nextInt();


    }

    public static void DeleteFile() {
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }


    public static void WriteToFile() {
        try {
            FileWriter myWriter = new FileWriter(myObj.getName(), true); //True means append to file contents, False means overwrite
            for (int i = 0; i < books.size(); i++) {
                myWriter.write(books.get(i)+"\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void CreateFile() {
        try {
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void ReadFile() {
        try {
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

        public static String getBookDetails () {
            int ISBN = Integer.parseInt(getInput("Enter the books ISBN"));
            String BookName = getInput("Enter the book's name");
            String bookAuthor = getInput("Enter the books author");
            String bookGenre = getInput("Enter the books genre");
            return (ISBN + "," + BookName + "," + bookAuthor + ',' + bookGenre);
        }

        public static String getInput (String prompt){
            System.out.println(prompt);
            Scanner input = new Scanner(System.in);
            return input.nextLine();
        }



                }

