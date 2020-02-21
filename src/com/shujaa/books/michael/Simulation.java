package com.shujaa.books.michael;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.*;

public class Simulation {
    public int B; // number of books
    public int L; // number of libraries
    public int D; // number of days

    public int counter = 0;

    public LinkedList<Library> libraries;
    public LinkedList<Book> allBooks;
    public LinkedList<Library> solution;
    public Set<Integer> scannedBooks;
    public LinkedList<Library> bestSolution;


    public int score = 0;

    public Simulation(int B, int L, int D){
        this.B = B;
        this.L = L;
        this.D = D;

        libraries = new LinkedList<>();
        allBooks = new LinkedList<>();
        solution = new LinkedList<>();
        scannedBooks = new TreeSet<>();
    }

    public int whichBookToScan(Library lib, int counter){
        int result = -1;
        int tempCounter = counter;
        Library tempLibrary = new Library(lib.N,lib.signUpDays,lib.booksShippedInADay,lib.index);

        if(counter > D ){
            return result;
        }

        result = 0;
        while(tempCounter < D){
            if(result < lib.books.size()){
                int count = 0;
                for(int i = result; i < lib.booksShippedInADay; i ++){

                    if(i < lib.books.size() ){
                        Book book = lib.books.get(i);
                        boolean isContained = scannedBooks.contains(book.index);
                        tempLibrary.books.addLast(book);
                        if(!isContained)
                            score += book.score;
                        else{
                            count ++;
                            if(count == lib.booksShippedInADay){
                                count=0;
                                //tempCounter -= 1;
                            }

                        }

                        scannedBooks.add(book.index);

                    }else {
                        break;
                    }
                    result = i;

                }
            }else {
                break;
            }
            tempCounter += 1;
        }
        lib = tempLibrary;
        return result;
    }


    public void solve(){

        int bestScore = 0;
        for(int i = 0 ; i < 1; i ++){
            //Collections.shuffle(libraries, new Random());
            score = 0;
            for(Library lib: libraries){
                if(counter < D){
                    int result = whichBookToScan(lib,counter);
                    if(result < 0){
                        break;
                    }else{
                        solution.addLast(lib);
                        counter += lib.signUpDays;
                    }


                }
            }

            if(score > bestScore){
                bestScore = score;
                bestSolution = solution;
            }

        }
        solution = bestSolution;
        score = bestScore;



    }


    public static void main(String [] args){
        String[] fileNames = {"a_example.txt","b_read_on.txt","c_incunabula.txt","d_tough_choices.txt","e_so_many_books.txt","f_libraries_of_the_world.txt"};

        int total = 0;
        for(int i = 0; i < fileNames.length; i ++ ){
            String fileName = fileNames[i];
            System.out.println("Solving ....... >>>>>>> "+ fileName);
            FileManager fileManager = new FileManager(fileName);

            Simulation simulation = fileManager.readFile();

            simulation.solve();
            total += simulation.score;
            System.out.println("Score "+ simulation.score);
            fileManager.write();
        }

        System.out.println("Total : "+ total);
    }

}
