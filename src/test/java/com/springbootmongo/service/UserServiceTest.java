package com.springbootmongo.service;

import com.springbootmongo.entity.User;
import com.springbootmongo.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @ParameterizedTest
    // We are passing here 4 different, different username to test our api using csv source we can pass here Csv file also it will pass username one by one in parameter
//    @CsvSource({"mukesh@gmail.com", "ram", "Krishna@gmail.com", "mukesh"})
    @ValueSource(strings = {"mukesh@gmail.com", "ram", "Krishna@gmail.com"})
    @Disabled
    public void findByUserNameTest(String userName) {
        User user = userRepository.findByUserName(userName);
        assertTrue(!user.getJournalEntries().isEmpty(), "This test case failed because name " + userName + "is empty");
        // In assert we can pass second parameter as a message
        assertNotNull(userRepository.findByUserName(userName), "Failed for name" + userName + "because this is empty name");
    }

    // @ArgumentsSource we can pass object as well
    @ArgumentsSource(UserArgumentProvider.class)
    @ParameterizedTest
    @Disabled
    public void createUserTest(User user) {
//        assertNotNull(userService.createUser(user));
    }

// In this method we are testing using assertEquals we are passing 2 value and performing addition and checking the result as true or false
    @Disabled
    // This annotation use to pass parameter in method
    @ParameterizedTest
    // Using this annotation we are passing value it will pass value line by line
    @CsvSource({"5,2,7", "7,2,9"})
    public void test(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }
}
