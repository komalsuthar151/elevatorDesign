/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.complexelevator;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author KomaL
 */
public class ComplexElevator {

    public static void main(String[] args) {

        ComplexElevator e = new ComplexElevator();
        e.askPassanger();
    }

    Scanner sc = new Scanner(System.in);
    //variables
    final int maxP = 20;
    final int maxF = 20;
    final int minP = 0;
    final int minF = 1;

    int curF = 1;
    int desF = 0;
    int passFloor = 0;
    int numOfPassenger = 0;
    boolean isDoorOpen = false;
    int[] dest_list = new int[maxF];

    ArrayList<Integer> listOfFloor  = new ArrayList<>();

    void startSimulatoin() {
        println("Welcome to the elevator");
        println("");
        delay(5000);
    }

    void askPassanger() {
        isDoorOpen = false;
        println("Elevetor opening...");
        delay(1500);
        isDoorOpen = true;
        println(curF + "F | How many passanger:");
        numOfPassenger = sc.nextInt();
        if (numOfPassenger < minP || numOfPassenger > maxP) {
            println("please enter valid number of passenger");
            askPassanger();
        } else {
//            listOfFloor;
            for (int i = 0; i < numOfPassenger; i++) {
                int floor = askPassangerFloor(i);
                if (!listOfFloor.contains(floor)) {
                    listOfFloor.add(floor);
                }
            }
//            for(int i : dest_list){
//            println("All the destination" + i);
//            }
//            
//            for(int a =0; a < listOfFloor.size(); a++){
//                println("unique destination" + listOfFloor.get(a));
//            }
            println("Elevator closing");
            delay(1500);
            isDoorOpen = false;
            initialize_elevator();
        }
    }

    int askPassangerFloor(int id) {
        boolean isValidEntry = false;
        int floor = 0;
        while (!isValidEntry) {
            println("Passanger #" + (id + 1) + "enter your floor");
            floor = sc.nextInt();

            if (floor < minF || floor > maxF) {
                println("Enter valid floor[1-20]");
            } else if (floor == curF) {
                println("Current floor " + curF);
            } else {
                dest_list[floor - 1]++;
                isValidEntry = true;
            }
        }
        return floor;
    }

    void initialize_elevator() {
        for (int a = 0; a < listOfFloor.size(); a++) {
            int shortest = findShortest();
            delay(1500);
            while (curF < shortest) {
                up();
            }
            while (curF > shortest) {
                down();
            }
            while (dest_list[shortest - 1] > 0) {
                println("Unloading passanger" + "(" + dest_list[shortest - 1]-- + ")" + "at" + curF);
            }
            while(dest_list[shortest -1] <= 20){
                println("picking up passenger" + dest_list[shortest - 1]++);
                askPassanger();
            }
        }
        askPassanger();
    }

    void up() {
        println(curF++ + "F |Going up");
    }

    void down() {
        println(curF-- + "F |Going down");
    }

    void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
        }
    }

    void println(Object o) {
        System.out.println(o);
    }

    int findShortest() {
        int shortest = Math.abs(curF - listOfFloor.get(0));
        int id = 0;
        for (int a = 1; a < listOfFloor.size(); a++) {
            if (shortest > Math.abs(curF - listOfFloor.get(a))) {
                shortest = Math.abs(curF - listOfFloor.get(a));
                id = a;
            }
        }
        shortest = listOfFloor.get(id);
        listOfFloor.set(id, 20);
        return shortest;
    }

}
