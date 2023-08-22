package org.project;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.UUID;

public class NotepadTests {
    Notepad testNotepad;

    @BeforeTest
    public void initialize() {
        testNotepad = new Notepad();
    }

    @Test
    public void addRecordTest_addOneRecord() {
        Record testRecord = testNotepad.addRecord("TestRecord", "");
        Record notepadRecord = testNotepad.getRecordById(testRecord.getId());

        Assert.assertTrue((notepadRecord.getName()==testRecord.getName())&&(notepadRecord.getSummary()==testRecord.getSummary()));
    }

    @Test
    public void addRecordTest_recordsNotRepeat() {
        Record testRecord;
        boolean result = false;

        for (int i = 0; i < 10; i++) {
            testRecord = testNotepad.addRecord("TestRecord-" + i, "");
            result = result || getCountOfRepeat(testRecord.getId()) != 1;
        }

        Assert.assertFalse(result);
    }

    private int getCountOfRepeat(UUID id) {
        int count = 0;

        for (Record record : testNotepad.getAllRecords()) {
            if (record.getId() == id) count++;
        }
        return count;
    }

    @Test
    public void deleteAllRecordsTest() {
        for (int i = 0; i < 10; i++) {
            testNotepad.addRecord("TestRecord-" + i, "");
        }
        testNotepad.deleteAllRecords();

        Assert.assertEquals(testNotepad.getAllRecords().size(), 0);
    }

    @Test
    public void deleteRecordTest_deleteOneFromOne() {
        Record record = testNotepad.addRecord("TestRecord", "");

        testNotepad.deleteRecordById(record.getId());

        Assert.assertFalse(testNotepad.idIsExist(record.getId()));
    }

    @Test
    public void deleteRecordTest_deleteOneFromTen() {
        Record record = testNotepad.addRecord("TestRecord", "");
        for (int i = 0; i < 9; i++) {
            testNotepad.addRecord("TestRecord-" + i, "");
        }

        testNotepad.deleteRecordById(record.getId());

        Assert.assertFalse(testNotepad.idIsExist(record.getId()) && testNotepad.getAllRecords().size() == 9);
    }

    @Test
    public void editRecordTest() {
        Record oldRecord = testNotepad.addRecord("TestRecord", "old summary");
        Record updateRecord = testNotepad.editRecord(0, "new summary");

        Assert.assertNotEquals(updateRecord, oldRecord.getSummary());
    }

}
