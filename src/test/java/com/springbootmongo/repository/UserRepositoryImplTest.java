package com.springbootmongo.repository;

import com.springbootmongo.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepositoryImplTest {
    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    public void testSaveNewUser() {
        List<User> userForSA = userRepository.getUserForSA();
        Assertions.assertNotNull(userForSA);
    }
}
