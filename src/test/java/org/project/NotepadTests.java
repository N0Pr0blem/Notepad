package org.project;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NotepadTests {
    Notepad testNotepad;

    @BeforeTest
    public void initialize() {
        testNotepad = new Notepad();
    }

    @Test
    public void addRecordTest_addOneRecord() {
        Record testRecord = testNotepad.addRecord("TestRecord", "");

        Assert.assertTrue(testNotepad.idIsExist(testRecord.getId()));

        Record notepadRecord = testNotepad.getRecordById(testRecord.getId());

        Assert.assertEquals(notepadRecord, testRecord);
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
        Record record = testNotepad.addRecord("TestRecord", "TestRecord");
        for (int i = 0; i < 9; i++) {
            testNotepad.addRecord("TestRecord-" + i, "");
        }

        testNotepad.deleteRecordById(record.getId());

        Assert.assertFalse(testNotepad.idIsExist(record.getId()));
        Assert.assertNotEquals(testNotepad.getAllRecords().size(), 10);
    }

    @Test
    public void editRecordTest() {
        String summary = "old summary";
        Record record = testNotepad.addRecord("TestRecord", summary);
        summary = "new summary";
        record.setSummary(summary);
        Assert.assertEquals(summary, record.getSummary());
    }

}
