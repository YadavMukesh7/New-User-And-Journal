package com.springbootmongo.service;

import com.springbootmongo.entity.JournalEntry;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface JournalEntryService {
    public JournalEntry createJournalEntry(JournalEntry journalEntry, String userName);

    public JournalEntry updateJournalEntry(ObjectId id, JournalEntry updatedEntry);

    public boolean deleteJournalEntry(ObjectId id, String userName);

    public Optional<JournalEntry> findById(ObjectId id);

    public List<JournalEntry> findAll();
}
