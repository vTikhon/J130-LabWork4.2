package ru.avalon.vergentev.j130.labwork4b;
import java.util.Random;

public class DataBase {
    private int numberOfReaders;
    private int numberOfWriters;

    public synchronized void connectForReading () throws InterruptedException {
        if (numberOfReaders == 0) {
            System.out.println(Thread.currentThread().getName() + " checks that DB is empty. Total readers: " + numberOfReaders + " and writers: " + numberOfWriters);
        }
        numberOfReaders = numberOfReaders + 1;
        System.out.println(Thread.currentThread().getName() + " started reading. Total readers: " + numberOfReaders  + " and writers: " + numberOfWriters);
    }

    public synchronized void connectForWriting () throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " checks that DB is empty. Readers: " + numberOfReaders + " and Writers: " + numberOfWriters);
        numberOfWriters = numberOfWriters + 1;
        System.out.println(Thread.currentThread().getName() + " started writing. Total readers: " + numberOfReaders  + " and writers: " + numberOfWriters);
        Thread.sleep(new Random().nextInt(1000, 2000));  //time of writing
        numberOfWriters = numberOfWriters - 1;
        System.out.println(Thread.currentThread().getName() + " finished writing. Total readers: " + numberOfReaders  + " and writers: " + numberOfWriters);
    }

    public synchronized int getNumberOfReaders() {
        return numberOfReaders;
    }

    public synchronized void setNumberOfReaders(int numberOfReaders) {
        this.numberOfReaders = numberOfReaders;
    }

    public synchronized int getNumberOfWriters() {
        return numberOfWriters;
    }

    public synchronized void setNumberOfWriters(int numberOfWriters) {
        this.numberOfWriters = numberOfWriters;
    }
}
