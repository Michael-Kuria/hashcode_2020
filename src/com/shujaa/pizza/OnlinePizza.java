package com.shujaa.pizza;

import java.util.LinkedList;
import java.util.List;

public class OnlinePizza {
    private int M;
    private int N;
    private LinkedList<Integer> solutionList;
    private int[] solution;
    public int[] slices;
    public int foundSlices;

    public OnlinePizza(Pizza p){
        this.M = p.M;
        this.N = p.N;
        this.slices = p.slices;
    }

    public void solve(){
        int j = N;
        int bestSum = 0;
        while(j >= 0){
            j --;

            LinkedList<Integer> v = new LinkedList<>();
            int sum = 0;
            int prevSum = 0;
            int i = j;
            for(i = j; i >= 0; i --){
                sum += slices[i];
                if(sum == M){
                    prevSum = sum;
                    v.addLast(i);
                    break;
                }else if(sum < M){
                    prevSum = sum;
                    v.addLast(i);
                }else{
                    sum -= slices[i];

                }

            }

            if(prevSum == M || prevSum > bestSum){
                solutionList = new LinkedList<>();
                bestSum = prevSum;
                foundSlices = bestSum;
                for(int k: v){
                    solutionList.addLast(k);
                }
            }

            if(prevSum == M){
                break;
            }else if(prevSum < bestSum){
                break;
            }

        }
    }

    public void convert(){
        solution = new int[solutionList.size()];
        int j = 0;
        for(int i : solutionList){
            solution[j] = i;
            j ++;

        }
    }

    public static void main(String [] args){
        String [] fileNames = {"d_quite_big.in","a_example.in","b_small.in","c_medium.in","e_also_big.in"};

        for(int i = 0 ; i < fileNames.length ; i ++){

            System.out.println("Solving ....... "+ fileNames[i]);

            Pizza pizza = null;
            OnlinePizza onlinePizza = null;
            FileManager fileManager = new FileManager( fileNames[i]);
            pizza = fileManager.readFile();

            onlinePizza = new OnlinePizza(pizza);

            onlinePizza.solve();
            onlinePizza.convert();
            pizza.solution = onlinePizza.solution;
            fileManager.write();

            System.out.println("Found slices "+ onlinePizza.foundSlices+ " Lost slices " + (onlinePizza.M - onlinePizza.foundSlices));
            System.out.println("Value of N "+ onlinePizza.N);
            System.out.println();

        }
    }


}
