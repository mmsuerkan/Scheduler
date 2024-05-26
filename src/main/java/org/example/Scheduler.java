package org.example;

import edu.princeton.cs.algs4.MaxPQ;


public class Scheduler {
        private static class Task implements Comparable<Task> {
            String name;
            long deadline;
            long duration;

            public Task(String name, long deadline, long duration) {
                this.name = name;
                this.deadline = deadline;
                this.duration = duration;
            }

            @Override
            public int compareTo(Task other) {
                return Long.compare(other.deadline, this.deadline); // MaxPQ kullanarak son teslim tarihine göre
            }
        }

    private MaxPQ<Task> pq;
    private long currentTime;

    public Scheduler() {
        pq = new MaxPQ<>();
        currentTime = 0;
    }

    public void scheduleTask(String name, long deadline, long duration) {
        Task task = new Task(name, deadline, duration);
        pq.insert(task);
        System.out.println(currentTime + ": adding " + name + " with deadline " + deadline + " and duration " + duration);
    }

    public void runUntil(long time) {
        while (currentTime < time) {
            if (pq.isEmpty()) {
                currentTime = time;
                break;
            }

            Task task = pq.delMax();
            System.out.println(currentTime + ": busy with " + task.name + " with deadline " + task.deadline + " and duration " + task.duration);

            long timeToAdvance = Math.min(task.duration, time - currentTime);
            currentTime += timeToAdvance;
            task.duration -= timeToAdvance;

            if (task.duration > 0) {
                pq.insert(task); // Kalan süre ile yeniden ekle
                System.out.println(currentTime + ": adding " + task.name + " with deadline " + task.deadline + " and duration " + task.duration);
            } else {
                if (currentTime > task.deadline) {
                    System.out.println(currentTime + ": done with " + task.name + " (late)");
                } else {
                    System.out.println(currentTime + ": done with " + task.name);
                }
            }
        }
    }
}


