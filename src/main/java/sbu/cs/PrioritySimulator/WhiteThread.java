package sbu.cs.PrioritySimulator;

import java.util.concurrent.CountDownLatch;

public class WhiteThread extends ColorThread {

    private static final String MESSAGE = "hi finished blacks, hi finished blues!";

    // new attribute CountDownLatch for white class
    private CountDownLatch CDLatch;

    public WhiteThread(CountDownLatch CDLatch) {
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
