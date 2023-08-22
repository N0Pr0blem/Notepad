package org.project;

import java.util.ArrayList;
import java.util.UUID;

public class Notepad {
    private final ArrayList<Record> allRecords = new ArrayList<>();


    public Record addRecord(String name, String summary) {
        Record record = new Record(name, summary);
        allRecords.add(record);
        return record;
    }

    public ArrayList<Record> getAllRecords() {
        return allRecords;
    }

    public void deleteRecordById(UUID id) {
        allRecords.remove(getRecordById(id));
    }

    public Record getRecordById(UUID id) {
        for (Record record : allRecords) {
            if (record.getId() == id) {
                return record;
            }
        }
        return new Record("Empty", "");
    }

    public void deleteAllRecords() {
        allRecords.clear();
    }

    public boolean idIsExist(UUID id) {
        for (Record record : allRecords) {
            if (record.getId() == id) return true;
        }

        return false;
    }

    public Record getRecordByIndex(int index) {
        return allRecords.get(index);
    }

    public boolean indexIsExist(int index) {
        return index >= 0 && index < allRecords.size();
    }

    public Record editRecord(int index, String summary) {
        allRecords.get(index).setSummary(summary);
        return allRecords.get(index);
    }
}
