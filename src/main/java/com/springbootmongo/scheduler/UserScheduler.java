package com.springbootmongo.scheduler;

import com.springbootmongo.entity.JournalEntity;
import com.springbootmongo.entity.User;
import com.springbootmongo.repository.UserRepositoryImpl;
import com.springbootmongo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class UserScheduler {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepositoryImpl userRepository;

    public void fetchUserAndSendSaMail() {
        List<User> userForSA = userRepository.getUserForSA();
        List<List<JournalEntity>> journalList = userForSA.stream().map(User::getJournalEntries).toList();
        List<JournalEntity> list = journalList.stream().flatMap(x -> x.stream().filter(y -> y.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))).toList();
        System.out.println("After filtering by date " + list.toString());
        System.out.println(list.toString());
        List<String> listTitle = list.stream().map(x -> x.getTitle()).toList();
        System.out.println(listTitle.toString());
        String entry = String.join(" ", listTitle);
        System.out.println("The entry is " + entry);
    }


   // @Scheduled(fixedRate = 5000)
    public void cronJob() {
        fetchUserAndSendSaMail();
    }
}
