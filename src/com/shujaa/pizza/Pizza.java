package com.shujaa.pizza;

public class Pizza {

    public int M; // maximum slices of pizza
    public int N; // the number of different types of pizza
    public int[] slices;
    public int[] summedSlices; // cummulative sum of pizzaSlices
    public int[] solution;
    public int foundSlices;


    public Pizza(int M, int N){
        this.M = M;
        this.N = N;

        slices = new int[N];
        summedSlices = new int[N];
        foundSlices = 0;
    }

    public void addSolution(int start, int end, int remove){
        if(remove < 0){
            solution = new int[end + 1];
        }else {
            solution = new int[end];
        }

        int j = 0;
        for(int i = 0; i <= end; i ++){
            if(i != remove){
                solution[j] = i;
                foundSlices += slices[i];
                j ++;
            }
        }

    }

    public int findBestIndexToStart(){

        int best = 0;
        for(int i = 0 ; i < N; i ++){
            if(summedSlices[i] == M){
                best = i;
                break;
            }

            if(summedSlices[best] < summedSlices[i] && summedSlices[i] < M ){
                best = i;
            }else if(summedSlices[i] > M){
                break;
            }

        }
        return best;

    }
    public int findBestIndexToRemove(int value, int start, int end){

        int best = -1;
        int difference = M - (value - slices[0]);

        if(value < M)
            return best;

        for(int i = 0; i < end; i ++){
            int d = M - (value - slices[i]);
            if(d >= 0){
                difference = d;
                best = i;
                break;
            }
        }
        return best;
    }

    public void solve(){
        int i = findBestIndexToStart();

        int difference = (M - summedSlices[i]);
        int indexToRemove = -1;
        int bestIndex = i;
        for(int j = i; j < N; j ++){
            int d;
            int k = findBestIndexToRemove(summedSlices[j], 0, j);

            if(k >= 0){
                d = M - (summedSlices[j] - slices[k]);
            }else {
                d = M - summedSlices[j];
            }
            if(difference > d && d >= 0){
                difference = d;
                indexToRemove = k;
                bestIndex = j;
            }

        }

        addSolution(0,bestIndex,indexToRemove);
    }

    public static void main(String[] args){
        String [] fileNames = {"a_example.in","b_small.in","c_medium.in","d_quite_big.in","e_also_big.in"};

        for(int i = 0 ; i < fileNames.length ; i ++){

            System.out.println("Solving ....... "+ fileNames[i]);

            Pizza pizza = null;
            FileManager fileManager = new FileManager(fileNames[i]);
            pizza = fileManager.readFile();
            pizza.solve();
            fileManager.write();

            System.out.println("Found slices "+ pizza.foundSlices+ " Lost slices " + (pizza.M - pizza.foundSlices));
            System.out.println();

        }
    }
}
