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
                    int recordIndex = getChooseRecordIndex(notepad);
                    Record chooseRecord = notepad.getRecordByIndex(recordIndex);
                    System.out.println("Enter new summary: ");
                    notepad.editRecord(chooseRecord.getId(), reader.nextLine());
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
            System.out.println(i + ". " + allRecords.get(i).getName());
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
        System.out.println("1. - Create record;\n2. - Read record;\n3. - Edit record;\n4. - Show all records;" + "\n5. - Delete record;\n6. - Delete all records;" + "\n0. - Exit.");
    }
}
