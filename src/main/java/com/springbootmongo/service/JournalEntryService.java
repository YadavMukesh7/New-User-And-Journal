package com.springbootmongo.service;

import com.springbootmongo.entity.JournalEntity;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface JournalEntryService {
    public JournalEntity createJournalEntry(JournalEntity journalEntry, String userName);

    public JournalEntity updateJournalEntry(ObjectId id, JournalEntity updatedEntry);

    public boolean deleteJournalEntry(ObjectId id, String userName);

    public Optional<JournalEntity> findById(ObjectId id);

    public List<JournalEntity> findAll();
}
