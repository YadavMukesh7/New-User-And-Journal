package com.springbootmongo.cache;

import com.springbootmongo.entity.ConfigJournalAppEntity;
import com.springbootmongo.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    public Map<String, String> appCache;
    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    @PostConstruct
    public void init() {
        appCache = new HashMap<>();
        List<ConfigJournalAppEntity> configJournalAppEntityList = configJournalAppRepository.findAll();
        configJournalAppEntityList.forEach(cj -> appCache.put(cj.getKey(), cj.getValue()));
    }

    public enum keys {
        WEATHER_API
    }
}
