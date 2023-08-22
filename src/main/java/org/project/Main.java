package org.project;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Notepad notepad = new Notepad();
        boolean inApp = true;
        String name, summary;
        Record foundRecord;

        while (inApp) {
            showAllCommand();
            switch (getCommand()) {
                case 1 -> {
                    System.out.print("Enter the name of record - ");
                    name = reader.nextLine();
                    System.out.println("Enter the summary:");
                    summary = reader.nextLine();
                    notepad.addRecord(name, summary);
                }
                case 2 -> {
                    showAllRecords(notepad.getAllRecords());
                    foundRecord = notepad.getRecordByIndex(getChooseRecordIndex(notepad));
                    System.out.println(foundRecord.getSummary());
                }
                case 3 -> {
                    showAllRecords(notepad.getAllRecords());
                    System.out.println("Enter new summary: ");
                    notepad.editRecord(getChooseRecordIndex(notepad), reader.nextLine());
                }
                case 4 -> showAllRecords(notepad.getAllRecords());
                case 5 -> {
                    showAllRecords(notepad.getAllRecords());
                    foundRecord = notepad.getRecordByIndex(getChooseRecordIndex(notepad));
                    notepad.deleteRecordById(foundRecord.getId());
                }
                case 6 -> notepad.deleteAllRecords();
                case 0 -> inApp = false;
            }
        }
    }

    private static void showAllRecords(ArrayList<Record> allRecords) {
        for (int i = 0; i < allRecords.size(); i++)
            System.out.println(i + ". " + allRecords.get(i));
    }

    private static int getCommand() {
        Scanner reader = new Scanner(System.in);
        int index;

        do {
            index = reader.nextInt();
        } while (index < 0 || index >= 9);

        return index;
    }

    private static int getChooseRecordIndex(Notepad notepad) {
        Scanner reader = new Scanner(System.in);
        int index;

        do {
            index = reader.nextInt();
        } while (!notepad.indexIsExist(index));

        return index;
    }


    private static void showAllCommand() {
        System.out.println("""
                1. - Create record;
                2. - Read record;
                3. - Edit record;
                4. - Show all records;
                5. - Delete record;
                6. - Delete all records;
                0. - Exit.""");
    }
}
