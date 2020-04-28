package com.shujaa.books.michael;

import javafx.beans.property.SimpleIntegerProperty;

import java.io.IOException;
import java.util.*;

public class Simulation {
    public int B; // number of books
    public int L; // number of libraries
    public int D; // number of days

    public int counter = -1;
    public int possibleScore = 0;

    public LinkedList<Library> libraries;
    public LinkedList<Library> testLibraries;
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
        testLibraries = new LinkedList<>();
    }

    public void updateLibraries(){
        Set<Book> books = new TreeSet<>();
        for(Library lib : testLibraries){
            LinkedList<Book> newBooks = new LinkedList<>();
            for(Book bk : lib.books){
                int size = books.size();
                books.add(bk); //costly
                if(books.size() != size){
                    newBooks.addLast(bk);
                    lib.value += bk.score;
                }
            }
            lib.books = newBooks;
        }

        testLibraries.sort(new Comparator<Library>() {
            @Override
            public int compare(Library o1, Library o2) {
                return ((Integer)o1.value).compareTo(o2.value);
            }
        });
        Collections.reverse(testLibraries);
    }

    public int scanBooks(Library lib){
        int tempCounter = this.counter;

        if(tempCounter >= D ){
            return -1;
        }

        int i = 0;
        LinkedList<Book> newBooks = new LinkedList<>();
        do{

            int j = 0;
            while(j < lib.booksShippedInADay && i < lib.books.size() ){
                Book bk = lib.books.get(i);
                boolean bool = scannedBooks.contains(bk.index);

                if(!bool){
                    j ++;
                    this.score += bk.score;
                    scannedBooks.add(bk.index);
                    newBooks.addLast(bk);
                }
                i ++;
            }

            tempCounter += 1; // increament by one daybk

        }while(tempCounter < D);

        lib.books = newBooks;
        if(newBooks.size() == 0){
            return -1;
        }

        return 1;
    }

    /*public int whichBookToScan(Library lib, int counter){
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
                        if(!isContained){
                            score += book.score;
                            scannedBooks.add(book.index);
                            tempLibrary.books.addLast(book);
                        }

                        else{
                            *//*count ++;
                            if(count == lib.booksShippedInADay){
                                count=0;
                                //tempCounter -= 1;
                            }*//*

                        }

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
*/

    public void solve(){

        int bestScore = 0;
        int i = 0;
        testLibraries.addAll(libraries);
        updateLibraries();

        do{
            this.score = 0;
            this.scannedBooks.clear();
            this.solution = new LinkedList<>();
            this.counter = -1;

            int afterSignUp = 0;
            for(Library lib: testLibraries){
               //if(lib.signUpDays < i ){
               //System.out.println("Library Books value "+lib.value +" signUp days " + lib.signUpDays);
                    afterSignUp = counter + lib.signUpDays;
                    if(afterSignUp < D){
                        int result = scanBooks(lib);
                        if(result < 0){
                            break;
                        }else{
                            if(lib.books.size() > 0)
                                solution.addLast(lib);
                            counter = afterSignUp;
                        }


                    }
             //}

           }

            i ++;


            Collections.shuffle(this.libraries, new Random());
            testLibraries.clear();
            testLibraries.addAll(libraries);
            updateLibraries();

            if(score > bestScore){
               // System.out.println("Am here");
                bestScore = score;
                bestSolution = solution;
            }


        }while(i < 1);

        solution = bestSolution;
        score = bestScore;

    }

    public int calculateScore(){
        int score = 0;

        for(Integer i : scannedBooks){
            score += allBooks.get(i).score;
        }
        return score;
    }

    public static void main(String [] args) throws IOException {
        //{"a_example.txt","b_read_on.txt","c_incunabula.txt",, "e_so_many_books.txt","f_libraries_of_the_world.txt" "d_tough_choices.txt"
        String[] fileNames = {"d_tough_choices.txt","f_libraries_of_the_world.txt"};

        int total = 0;
        int possibleTotal = 0;
        for(int i = 0; i < fileNames.length; i ++ ){
            String fileName = fileNames[i];
            System.out.println("Solving ....... >>>>>>> "+ fileName);
            FileManager fileManager = new FileManager(fileName);

            Simulation simulation = fileManager.readFile();

            simulation.solve();
            int score = simulation.calculateScore();
            total += simulation.score;
            possibleTotal += simulation.possibleScore;
            System.out.println("Score "+ simulation.score + " Calculated Score " + score);
            fileManager.write();
        }

        System.out.println("Total : "+ total +" Possible Total " + possibleTotal);
        System.out.println("Difference : " +(possibleTotal - total));
    }

}
