package sbu.cs.PrioritySimulator;

import java.util.concurrent.CountDownLatch;

public class BlueThread extends ColorThread {

    private static final String MESSAGE = "hi finished blacks, hi whites!";


    // new attribute CountDownLatch for blue class
    private CountDownLatch CDLatch;

    public BlueThread(CountDownLatch CDLatch) {
        this.CDLatch = CDLatch;
    }

    void printMessage() {
        super.printMessage(new Message(this.getClass().getName(), getMessage()));
    }

    @Override
    String getMessage() {
        return MESSAGE;
    }

    @Override
    public void run() {
        // TODO call printMessage
        printMessage();

        // call the countDown method
        CDLatch.countDown();
    }
}
