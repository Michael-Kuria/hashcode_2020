package com.shujaa.books.michael;

import java.util.LinkedList;

public class Library implements Comparable {

    public int N; // number of books
    public int signUpDays;
    public int booksShippedInADay;
    public LinkedList<Book> books;
    public int index;
    public int difference;
    public int value = 0;

    public Library(int N, int signUpDays, int booksShippedInADay, int index){
        this.N = N;
        this.signUpDays = signUpDays;
        this.booksShippedInADay = booksShippedInADay;
        this.index = index;




        books = new LinkedList<>();
    }



    @Override
    public String toString(){
        String str = index + " "+ books.size() +"\n";

        for(Book bk : books){
            str += bk.index+" ";
        }

        return str;

    }


    @Override
    public int compareTo(Object o) {

        return ((Integer)this.signUpDays).compareTo(((Library)o).signUpDays);
    }

   /* @Override
    public int compareTo(Object o) {

        return ((Integer)this.books.size()).compareTo(((Library)o).books.size());
    }*/
}
