package ru.avalon.vergentev.j130.labwork4b;

public class DataBase {
    private int numberOfReaders = 0;
    private int numberOfWriters = 0;

    public synchronized void reading () {
        if (numberOfReaders == 0) {
            System.out.println(Thread.currentThread().getName() + " checks that DB is empty. Total readers: " + numberOfReaders + " and writers: " + numberOfWriters);
        }
        numberOfReaders = numberOfReaders + 1;
        System.out.println(Thread.currentThread().getName() + " started reading. Total readers: " + numberOfReaders  + " and writers: " + numberOfWriters);
    }

    public synchronized void writing () {
        System.out.println(Thread.currentThread().getName() + " checks that DB is empty. Readers: " + numberOfReaders + " and Writers: " + numberOfWriters);
        numberOfWriters = numberOfWriters + 1;
        System.out.println(Thread.currentThread().getName() + " started writing. Total readers: " + numberOfReaders  + " and writers: " + numberOfWriters);
    }

    public int getNumberOfReaders() {
        return numberOfReaders;
    }

    public void setNumberOfReaders(int numberOfReaders) {
        this.numberOfReaders = numberOfReaders;
    }

    public int getNumberOfWriters() {
        return numberOfWriters;
    }

    public void setNumberOfWriters(int numberOfWriters) {
        this.numberOfWriters = numberOfWriters;
    }
}
