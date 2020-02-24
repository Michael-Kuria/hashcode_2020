package com.shujaa.books.michael;

public class Book implements Comparable {

    public int index;
    public int score;

    public Book(int index, int score){
        this.index = index;
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {
        return ((Integer)this.index).compareTo(((Book)o).index);
    }

}
