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
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author AndrzejZahorski
 * @ adapted by Piet
 */

public class AndrzejZahorskiOriginal {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Random r = new Random();
        int nrOfTasks = r.nextInt(5) + 3;
        TaskMachineOriginal taskMachine = new TaskMachineOriginal(nrOfTasks);

        for (int i = 0; i < nrOfTasks; i++) {
            taskMachine.go(i);
        }
        
        while (!taskMachine.tasksFinished()) {
            taskMachine.report();
            TimeUnit.SECONDS.sleep(1);
            if (r.nextBoolean()) taskMachine.pleaseStop(r.nextInt(nrOfTasks));
        }

        taskMachine.report();
        taskMachine.finish();
    }
}

//-----------------------------------------------------------------------
class TaskMachineOriginal {

    private final ExecutorService service;
    private final List<FutureTask<Integer>> futureTaskList;

    TaskMachineOriginal(int nrOfTasks) {
        System.out.println("This is task machine\n"
                + "You can start any number of tasks which are doing things by\n"
                + "taskMachine.go(1|2|3|...)");
        Random r = new Random();
        futureTaskList = new ArrayList<>();
        for (int i = 0; i < nrOfTasks; i++) {
            futureTaskList.add(new FutureTask<>(new MyTaskSelfMade(i, r.nextInt(5) + 4)));
        }
        service = Executors.newFixedThreadPool(2);
    }
    
    public boolean tasksFinished() {
        return futureTaskList.stream().allMatch(e -> e.isDone());
    }
    
    public void finish() {
        service.shutdown();
    }
    
    public void report() {
        System.out.println("**** report ***********");
        for (int i = 0; i < futureTaskList.size(); i++) {
            magicBoxPleaseLetMeKnow(i);
        }
    }

    private void magicBoxPleaseLetMeKnow(int taskNum) {
        FutureTask tmp = futureTaskList.get(taskNum);
        if (tmp.isCancelled()) {
            System.out.printf("Task no. %d is Cancelled\n", taskNum);
        } 
        else if (tmp.isDone()) {
            try {
               System.out.format("Task no. %d is Done. Result %d%n", taskNum, tmp.get());
            }
            catch (InterruptedException | ExecutionException e) {System.out.println("Exception");}
        } 
        else {
            System.out.printf("Task no. %d is RUNNING LIKE WILD HORSE\n ", taskNum);
        }
    }

    public void pleaseStop(int taskNum) {
        futureTaskList.get(taskNum).cancel(true);
    }

    public void go(int taskNum) {
        System.out.printf("Starting task no %d\n", taskNum);
        service.submit(futureTaskList.get(taskNum));
    }

    public Integer getNum(int taskNum)
            throws ExecutionException, InterruptedException {

        return futureTaskList.get(taskNum).get();
    }

    public List<FutureTask<Integer>> getFutureTaskList() {
        return futureTaskList;
    }
}

//---------------------------------------------------------------------
class MyTaskSelfMade implements Callable<Integer> {
    int result;
    long sleepSeconds;
    
    MyTaskSelfMade(int result, long secondsSleep) {
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
