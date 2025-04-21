package com.springbootmongo.service;

import com.springbootmongo.serviceImpl.EmailServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailServiceTest {
    @Autowired
    private EmailServiceImpl emailService;

    @Disabled
    @Test
    void testSendMail() {
        emailService.sendEmail("mukesh.yadav@bluethinkinc.com", "Java mail sender", "Hello how are you....!!!?");
    }
}
