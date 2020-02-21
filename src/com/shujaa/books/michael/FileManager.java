package com.shujaa.books.michael;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;

public class FileManager {

    public Simulation simulation;
    public String inputFile;
    public String outputFile;

    public FileManager(String inputFile){
        this.inputFile = inputFile;
        outputFile = inputFile.substring(0, inputFile.indexOf(".")) + ".out";
    }

    public Simulation readFile(){
        FileReader reader = null;
        Scanner scanner = null;
        try{

            reader = new FileReader(new File("src/com/shujaa/books/michael/resources/" + inputFile));
            scanner = new Scanner(reader);

            String[] info = scanner.nextLine().split(" ");

            int B = Integer.parseInt(info[0]);
            int L = Integer.parseInt(info[1]);
            int D = Integer.parseInt(info[2]);

            simulation = new Simulation(B,L,D);

            String[] books = scanner.nextLine().split(" ");

            for(int i = 0; i < B; i ++){
                int score = Integer.parseInt(books[i]);
                simulation.allBooks.addLast(new Book(i,score));
            }

            int index = 0;
            while(index < L){
                String[] libInfo = scanner.nextLine().split(" ");
                int N = Integer.parseInt(libInfo[0]);
                int T = Integer.parseInt(libInfo[1]);
                int M = Integer.parseInt(libInfo[2]);

                Library lib = new Library(N,T,M,index);

                String[] libBooks = scanner.nextLine().split(" ");

                for(int i = 0 ; i < libBooks.length; i ++){
                    int j = Integer.parseInt(libBooks[i]);
                    lib.books.addLast(simulation.allBooks.get(j));
                }

                lib.difference = lib.signUpDays + (lib.books.size() / lib.booksShippedInADay);
                Collections.sort(lib.books);
                simulation.libraries.addLast(lib);
                index ++;
            }
            Collections.sort(simulation.libraries);


            return simulation;

        } catch (FileNotFoundException e) {
            System.out.println(inputFile + " not found");
        }finally {
            scanner.close();
        }

        return simulation;
    }

    public void write(){
        PrintWriter writer = null;
        try{
            writer = new PrintWriter(new File("src/com/shujaa/books/michael/solution/" + outputFile));

            writer.println(simulation.solution.size());

            for(Library lib : simulation.solution){
                writer.println(lib);
            }


        }catch (FileNotFoundException e) {
            System.out.println("File location not found");
        }finally {
            writer.flush();
            writer.close();
        }
    }
}
