package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Driver {
    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();
        try (BufferedReader br = new BufferedReader(new FileReader("sampleinput1.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts[0].equals("schedule")) {
                    scheduler.scheduleTask(parts[1], Long.parseLong(parts[2]), Long.parseLong(parts[3]));
                } else if (parts[0].equals("run")) {
                    scheduler.runUntil(Long.parseLong(parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}