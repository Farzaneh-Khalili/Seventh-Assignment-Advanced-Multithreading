package sbu.cs.Semaphore;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.Semaphore;


public class Operator extends Thread {

    // use class DateTimeFormatter to show the time in this format :↷
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    Semaphore sem;
    public Operator(Semaphore sem, String name) {
        super(name);
        this.sem = sem;
    }

    @Override
    public void run() {
        try {
            System.out.println(getName() + " is waiting for a permit");
            // thread acquiring the lock
            sem.acquire();
            LocalTime time = LocalTime.now();


            for (int i = 0; i < 10; i++)
            {
                Resource.accessResource(); // critical section - a Maximum of 2 operators can access the resource concurrently

                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(getName() + " gets a permit at " + time.format(formatter));

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            // release the permit
            sem.release();
            LocalTime time = LocalTime.now();
            System.out.println(getName() + " released a permit at " + time.format(formatter));
        }
    }
}
