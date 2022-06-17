package ru.avalon.vergentev.j130.labwork4b;
import java.util.Random;

public class Writers implements Runnable {
    private final DataBase dataBase;

    public Writers(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            try {
                //sleeping while DB is busy
                while (dataBase.getNumberOfReaders() !=0 && dataBase.getNumberOfWriters() != 0) {
                    System.out.println(Thread.currentThread().getName() + " wants to write. Waiting for DB will be empty. Total readers: " + dataBase.getNumberOfReaders()  + " and writers: " + dataBase.getNumberOfWriters());
                    Thread.sleep(new Random().nextInt(1000));
                }
                //connect to DB for writing
                dataBase.writing();
                Thread.sleep(new Random().nextInt(3000, 5000));  //time of writing
                dataBase.setNumberOfWriters(dataBase.getNumberOfWriters() - 1);
                System.out.println(Thread.currentThread().getName() + " finished writing. Total readers: " + dataBase.getNumberOfReaders()  + " and writers: " + dataBase.getNumberOfWriters());
            } catch (InterruptedException e) {
                System.out.println("Error into run method at Writers " + e.getMessage());
            }
        }
        System.out.println("<<" + Thread.currentThread().getName() + ">> HAS FINISHED.");
    }
}
