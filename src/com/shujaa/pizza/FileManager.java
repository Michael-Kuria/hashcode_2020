package com.shujaa.pizza;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileManager {

    private String fileName;
    public Pizza pizza = null;

    public FileManager(String fileName){
        this.fileName = fileName;
    }


    public Pizza readFile(){
        FileReader reader = null;
        Scanner scanner = null;

        try{
            reader = new FileReader("src/com/shujaa/pizza/files/"+fileName);
            scanner = new Scanner(reader);

            //Read the first Line of the file for M and N
            String lineOne = scanner.nextLine();
            String [] params = lineOne.split(" ");

            int M = Integer.parseInt(params[0]);
            int N = Integer.parseInt(params[1]);

            //Initialize pizza
            pizza = new Pizza(M,N);

            String [] slices = scanner.nextLine().split(" ");

            for(int i = 0 ; i < N ; i ++) {
                //initialize both the slices and summedSlices array
                pizza.slices[i] = Integer.parseInt(slices[i]);
                pizza.summedSlices[i] = pizza.slices[i] +  ( i == 0 ? 0 : pizza.summedSlices[i-1]);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            scanner.close();
        }

        return pizza;
    }

    public void write(){

        PrintWriter writer = null;

        try{
            String destination = fileName.substring(0,fileName.indexOf("."))+".out";
            writer = new PrintWriter(new File("src/com/shujaa/pizza/output/online_"+destination));

            writer.println(pizza.solution.length);

            for(int i = 0 ; i < pizza.solution.length; i ++){
                if (i < pizza.solution.length - 1)
                    writer.print(pizza.solution[i] + " ");
                else
                    writer.println(pizza.solution[i]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            writer.flush();
            writer.close();
        }
    }


}
