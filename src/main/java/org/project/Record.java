package org.project;

import java.util.Objects;
import java.util.UUID;

public class Record {
    private final String name;
    private String summary;
    private final UUID id;


    public Record(String name, String summary) {
        this.name = name;
        this.summary = summary;
        id = UUID.randomUUID();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(name, record.name) && Objects.equals(summary, record.summary) && Objects.equals(id, record.id);
    }

}
