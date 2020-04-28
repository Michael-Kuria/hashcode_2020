package com.shujaa.books.michael;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class will validated the solution file and calculate the score of the solution;
 */
public class ValidateSolution {

    public String fileName;
    public Simulation simulation;
    public Library[]  solutionLibraries;
    public Set<Book> scannedBooks;

    public ValidateSolution(String fileName, Simulation simulation){
        this.fileName = "src/com/shujaa/books/michael/solution/"+fileName;
        this.simulation = simulation;
        scannedBooks = new TreeSet<>();
        readFile();
    }

    public void readFile(){
        FileReader reader = null;
        Scanner scanner = null;
        try{
            reader = new FileReader(new File(fileName));
            scanner = new Scanner(reader);

            int libCount = Integer.parseInt(scanner.nextLine());

            solutionLibraries = new Library[libCount];
            int numBooks = 0;
            int index = 0;
            for(int i = 0; i < libCount; i ++){

                LinkedList<Book> books = new LinkedList<>();
                if(scanner.hasNextLine()){
                    String[] libInfo = scanner.nextLine().split(" ");
                    index = convert(libInfo[0]);
                    numBooks = convert(libInfo[1]);

                }else{
                    break;
                }

                if(scanner.hasNextLine()){
                    String[] libBooks = scanner.nextLine().split(" ");


                    for(int j = 0; j < libBooks.length; j ++){

                        books.add(simulation.allBooks.get(convert(libBooks[j])));
                    }
                    solutionLibraries[i] = simulation.libraries.get(index);

                    solutionLibraries[i].books = books;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int validate(){
        int score = 0;
        int counter = -1;
        for(int i = 0 ; i < solutionLibraries.length; i ++ ){

            counter += solutionLibraries[i].signUpDays;
            int tempCounter = counter;
            if(counter < simulation.D){

                int k = 0;
                while(tempCounter < simulation.D ){

                    for(int j = 0; j < solutionLibraries[i].booksShippedInADay; j ++){
                        if(k < solutionLibraries[i].books.size()){
                            int size = scannedBooks.size();
                            scannedBooks.add(solutionLibraries[i].books.get(k));
                            if(size != scannedBooks.size()){
                                score += solutionLibraries[i].books.get(k).score;
                            }
                            k ++;
                        }else{
                            tempCounter = simulation.D + 100;
                            break;

                        }

                    }
                    tempCounter += 1;
                }

            }
        }
        return score;
    }


    public int convert(String str){
        return Integer.parseInt(str);
    }


    public static void main(String[] args){

        String[] fileNames = {"b_read_on.txt"};

        int total = 0;
        for(int i = 0; i < fileNames.length; i ++){
            FileManager fileManager = new FileManager(fileNames[i]);
            Simulation simulation = fileManager.readFile();
            ValidateSolution validate = new ValidateSolution(fileManager.outputFile,simulation );
            int score = validate.validate();

            System.out.println("Score for "+ fileManager.inputFile +" is : "+ score);
            total += score;
        }

        System.out.println("Total : "+ total);


    }
}
