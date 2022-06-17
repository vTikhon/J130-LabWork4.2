package ru.avalon.vergentev.j130.labwork4b;

public class Main {
    public static void main(String[] args) {
        DataBase dataBase = new DataBase();

        for (int i = 0; i < 3; i++) {
            new Thread(new Readers(dataBase), "Reader-" + (i+1)).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(new Writers(dataBase), "Writer-" + (i+1)).start();
        }


    }
}
