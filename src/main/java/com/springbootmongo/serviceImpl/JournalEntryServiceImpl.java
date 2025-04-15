package com.springbootmongo.serviceImpl;

import com.springbootmongo.entity.JournalEntry;
import com.springbootmongo.entity.User;
import com.springbootmongo.repository.JournalEntryRepository;
import com.springbootmongo.repository.UserRepository;
import com.springbootmongo.service.JournalEntryService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class JournalEntryServiceImpl implements JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public JournalEntry createJournalEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userRepository.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
//            user.setUserName(null);
            userRepository.save(user);
            return saved;
        } catch (Exception ex) {
            try {
                log.info(ex.getMessage());
                throw new Exception(ex.getMessage());
            } catch (Exception e) {
                log.info(e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    @Override
    public JournalEntry updateJournalEntry(ObjectId id, JournalEntry updatedEntry) {
        updatedEntry.setId(id);
        return journalEntryRepository.save(updatedEntry);
    }

    @Override
    @Transactional
    public boolean deleteJournalEntry(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userRepository.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userRepository.save(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Something went wrong while deleting entry");
        }
        return removed;
    }

    @Override
    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Override
    public List<JournalEntry> findAll() {
        return journalEntryRepository.findAll();
    }
}
