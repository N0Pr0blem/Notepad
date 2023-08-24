package org.project;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

import java.util.ArrayList;
import java.util.UUID;

public class Notepad {
    private final ArrayList<Record> allRecords = new ArrayList<>();
    private final Logger log = LogManager.getLogger(Notepad.class);


    public Record addRecord(String name, String summary) {
        Record record = new Record(name, summary);
        allRecords.add(record);
        log.info(String.format("Record '%s' (id: %s) added to notepad", record.getName(),record.getId().toString()));
        return record;
    }

    public ArrayList<Record> getAllRecords() {
        return allRecords;
    }

    public void deleteRecordById(UUID id) {
        Record record = getRecordById(id);
        allRecords.remove(record);
        log.info(String.format("Record %s (id: %s) has been deleted",record.getName(), id.toString()));
    }

    public Record getRecordById(UUID id) {
        for (Record record : allRecords) {
            if (record.getId() == id) {
                log.info(String.format("Record was found by id - %s",id.toString()));
                return record;
            }
        }
        log.error(String.format("Record was NOT found by id - %s",id.toString()));
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
        log.info(String.format("Record was found by index - %s",index));
        return allRecords.get(index);
    }

    public boolean indexIsExist(int index) {
        return index >= 0 && index < allRecords.size();
    }

    public void editRecord(UUID id, String summary) {
        Record result = getRecordById(id);
        result.setSummary(summary);
        log.info(String.format("Record's '%s' summary was changed to:\n'%s'\nsuccessfully",result.getName(),result.getSummary()));
    }
}
