package org.project;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.UUID;

public class Notepad {
    private final ArrayList<Record> allRecords = new ArrayList<>();
    private final Logger log = Logger.getLogger(Notepad.class);


    public Record addRecord(String name, String summary) {
        Record record = new Record(name, summary);
        allRecords.add(record);
        log.info("Record "+record.getName()+" added to notepad");
        return record;
    }

    public ArrayList<Record> getAllRecords() {
        log.info("All records have been received");
        return allRecords;
    }

    public void deleteRecordById(UUID id) {
        log.info("Record has been deleted");
        allRecords.remove(getRecordById(id));
    }

    public Record getRecordById(UUID id) {
        for (Record record : allRecords) {
            if (record.getId() == id) {
                log.info("Record was found by id");
                return record;
            }
        }
        log.error("Record was NOT found by id");
        return new Record("Empty", "");
    }

    public void deleteAllRecords() {
        allRecords.clear();
        log.info("All records have been deleted");
    }

    public boolean idIsExist(UUID id) {
        for (Record record : allRecords) {
            if (record.getId().equals(id)) return true;
        }

        return false;
    }

    public Record getRecordByIndex(int index) {
        log.info("Record was found by index");
        return allRecords.get(index);
    }

    public boolean indexIsExist(int index) {
        return index >= 0 && index < allRecords.size();
    }

    public void editRecord(UUID id, String summary) {
        Record result = getRecordById(id);
        result.setSummary(summary);
        log.info("Record changed successfully");
    }
}
