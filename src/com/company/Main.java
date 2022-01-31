package com.company;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.awt.print.Book;

import java.util.Scanner;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileWriter;

import java.io.IOException;

import java.util.ArrayList;

public class Main {

    private static int n = 0;
    private static ArrayList<String> books = new ArrayList<>();
    private static ArrayList<String> register = new ArrayList<>();
    private static int menuInt = 0;
    private static int loginSys = 0;
    private static String password;
    private static String username;
    private static String userLog;
    private static String userPass;
    private static String BookName2;
    private static String bookGenre2;
    private static String bookAuthor2;

    private static int booksToAdd = 1;


    private static final File myObj = new File("bookList.txt");
    private static final File myBorrow = new File("bookBorrow.txt");
    private static final File myLogin = new File("userLogins.txt");
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        CreateFile();
        CreateFile2();
        CreateFile3();

        loginSys();

        switch (loginSys) {

            case 1:
                logInDetails();

                break;

            case 2:
                Register();
                break;

            case 3:
                DeleteFileLogin();
                break;


        }


        for (int j = 0; j < 5; j++) {
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

                case 4:

                BookCheck();
                break;

                case 5:
                borrowRead();
                break;






            }

        }


    }
    public static void borrowRead() {
        try {
            Scanner myReader = new Scanner(myBorrow);
            while (myReader.hasNextLine()) {
                String data3 = myReader.nextLine();
                System.out.println(data3);

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public static void BookCheck() {

        String ISBN2 = getInput("Enter the books ISBN");
        String BookName = getInput("Enter the book's name");


        B:
        while (true) {



            try {
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {

                    String data = myReader.nextLine();
                    if (data.contains(ISBN2)) {
                        break B;

                    }


                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        try {
            FileWriter myWriter = new FileWriter(myBorrow.getName(), true); //True means append to file contents, False means overwrite

            myWriter.write(ISBN2 + " , " + BookName);
            myWriter.close();
            System.out.println("Successfully wrote your data to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }



    }




    public static void ShowMenu() {
        System.out.println("\n");
        System.out.println("Please choose an option from the menu: ");
        System.out.println("1 - add books ");
        System.out.println("2 - display books ");
        System.out.println("3 - delete file ");
        System.out.println("4 - borrow books");
        System.out.println("5 - borrowed books");

        Scanner input = new Scanner(System.in);
        menuInt = input.nextInt();


    }

    public static void loginSys() {
        System.out.println("\n");
        System.out.println("Please choose one of the following options: ");
        System.out.println("1 - Login ");
        System.out.println("2 - Register ");
        System.out.println("3 - Delete data ");

        Scanner input = new Scanner(System.in);
        loginSys = input.nextInt();


    }

    public static void logInDetails() {

       A: while (true) {


            System.out.println("\n");
            System.out.println("Please enter your username: \n");
            Scanner input1 = new Scanner(System.in);
            String userLog = input1.next();

            System.out.println("Now please enter your password \n");
            Scanner input2 = new Scanner(System.in);
            String userPass = input2.next();

            try {


                Scanner myReader = new Scanner(myLogin);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if (data.contains(userPass) && data.contains(userLog)) {
// need a data.split function to split data then compare the user input to registered value and if it contains it, it will return positive
                        break A;
                    }


                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }



    public static void Register() {

        System.out.println("\n");
        System.out.println("Please enter your username: \n");
        Scanner input1 = new Scanner(System.in);
        String username = input1.next();

        System.out.println("Now please enter your password \n");
        Scanner input2 = new Scanner(System.in);
        String password = input2.next();

        try {
            FileWriter myWriter = new FileWriter(myLogin.getName(), true); //True means append to file contents, False means overwrite

            myWriter.write(username + "," + password + "\n");


            myWriter.close();
            System.out.println("Successfully wrote your data to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        loginSys();


    }


    public static void DeleteFile() {
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    public static void DeleteFileLogin() {
        if (myLogin.delete()) {
            System.out.println("Deleted the file: " + myLogin.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    public static void WriteToFile() {
        try {
            FileWriter myWriter = new FileWriter(myObj.getName(), true); //True means append to file contents, False means overwrite
            for (int i = 0; i < books.size(); i++) {
                myWriter.write(books.get(i) + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void CreateFile2() {
        try {
            if (myLogin.createNewFile()) {
                System.out.println("File created: " + myLogin.getName());
            } else {
                System.out.println("File already exists.");
            }
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
    public static void CreateFile3() {
        try {
            if (myBorrow.createNewFile()) {
                System.out.println("File created: " + myBorrow.getName());
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

    public static String getBookDetails() {
        int ISBN = Integer.parseInt(getInput("Enter the books ISBN"));
        String BookName = getInput("Enter the book's name");
        String bookAuthor = getInput("Enter the books author");
        String bookGenre = getInput("Enter the books genre");
        n++;
        return (n + ")" + " " + ISBN + "," + BookName + "," + bookAuthor + ',' + bookGenre + "\n");

    }

    public static String getInput(String prompt) {
        System.out.println(prompt);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static int getIntInput(String prompt) {
        System.out.println(prompt);
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }




}

