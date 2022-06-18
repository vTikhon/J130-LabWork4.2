package ru.avalon.vergentev.j130.labwork4b;
import java.util.Random;

public class Writers implements Runnable {
    private final DataBase dataBase;

    public Writers(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void run() {
        System.out.println("<<" + Thread.currentThread().getName() + ">> HAS BEEN STARTED.");
        for (int i = 0; i < 4; i++) {
            try {
                //sleeping while DB is busy
                while (dataBase.getNumberOfReaders() != 0) {
                    System.out.println(Thread.currentThread().getName() + " wants to connect. Waiting for DB will be empty. Total readers: " + dataBase.getNumberOfReaders()  + " and writers: " + dataBase.getNumberOfWriters());
                    Thread.sleep(new Random().nextInt(1000));
                }
                //connect to DB for writing
                dataBase.connectForWriting();
            } catch (InterruptedException e) {
                System.out.println("Error into run method at Writers " + e.getMessage());
            }
        }
        System.out.println("<<" + Thread.currentThread().getName() + ">> HAS BEEN FINISHED.");
    }
}
