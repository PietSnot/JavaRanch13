/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaranch13;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Piet
 */
public class AndrzejZahorski {

}

class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Random r = new Random();
        TaskMachine taskMachine = new TaskMachine();
        System.out.println("TaskMachine started");

        taskMachine.go();
        
        while (!taskMachine.tasksDone()) {
            TimeUnit.SECONDS.sleep(1);
            for (int i = 0; i < 3; i++) {
                taskMachine.magicBoxPleaseLetMeKnow(i);
                if (r.nextBoolean()) taskMachine.pleaseStop(r.nextInt(3));
            }
        }
        taskMachine.stop();
    }
}

class TaskMachine {

    private final ExecutorService service;
    private final List<Future<Integer>> futureTaskList;
    private final List<MyTask> myTasks;

    TaskMachine() {
        System.out.println("This is task machine\n"
                + "You can start 3 tasks which are doing things by\n"
                + "taskMachine.go(1|2|3)");
        futureTaskList = new ArrayList<>();
        myTasks = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            myTasks.add(new MyTask(i, r.nextInt(10) + 1));
        }
        service = Executors.newFixedThreadPool(3);
    }
    
    public boolean tasksDone() {
        return futureTaskList.stream().allMatch(e -> e.isDone());
    }
    
    public void magicBoxPleaseLetMeKnow(int taskNum) {
        Future<Integer> tmp = futureTaskList.get(taskNum);
        if (tmp.isCancelled()) {
            System.out.printf("Task no. %d is CANCELLED\n", taskNum);
        } else if (tmp.isDone()) {
            System.out.printf("Task no. %d is DONE\n", taskNum);
        } else {
            System.out.printf("Task no. %d is RUNNING LIKE WILD HORSE%n ", taskNum);
        }
    }
    
    public void stop() {
        service.shutdown();
    }

    public void pleaseStop(int taskNum) {
        futureTaskList.get(taskNum).cancel(true);
    }

    public void go() {
        System.out.printf("Starting tasks, nr of tasks %d\n", myTasks.size());
        for (MyTask task: myTasks) futureTaskList.add(service.submit(task));
        
    }

    public Integer getNum(int taskNum)
            throws ExecutionException, InterruptedException {

        return futureTaskList.get(taskNum).get();
    }

    public List<Future<Integer>> getFutureTaskList() {
        return futureTaskList;
    }
}

class MyTask implements Callable<Integer> {
    int result;
    long sleepSeconds;
    MyTask(int result, long secondsSleep) {
        this.result = result;
        sleepSeconds = secondsSleep;
    }
    
    @Override
    public Integer call() {
        try {
            TimeUnit.SECONDS.sleep(sleepSeconds);
        }
        catch (InterruptedException e) {
            System.out.println("MyTask " + result + " is interrupted");
        }
        return result;
    }
}
