package ru.avalon.vergentev.j130.labwork4b;
import java.util.Random;

public class Readers implements Runnable {
    private final DataBase dataBase;

    public Readers(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            try {
                //sleeping while there are any writers
                while (dataBase.getNumberOfWriters() != 0) {
                    System.out.println(Thread.currentThread().getName() + " wants to read. Waiting for writer will get out. Total readers: " + dataBase.getNumberOfReaders()  + " and writers: " + dataBase.getNumberOfWriters());
                    Thread.sleep(new Random().nextInt(1000));
                }
                //connect to DB for reading
                dataBase.reading();
                Thread.sleep(new Random().nextInt(3000, 5000));  //time of reading
                dataBase.setNumberOfReaders(dataBase.getNumberOfReaders() - 1);
                System.out.println(Thread.currentThread().getName() + " finished reading. Total readers: " + dataBase.getNumberOfReaders()  + " and writers: " + dataBase.getNumberOfWriters());
            } catch (InterruptedException e) {
                System.out.println("Error into run method at Readers " + e.getMessage());
            }
        }
        System.out.println("<<" + Thread.currentThread().getName() + ">> HAS FINISHED.");
    }
}
