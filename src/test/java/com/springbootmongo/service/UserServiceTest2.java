package com.springbootmongo.service;

import com.springbootmongo.entity.User;
import com.springbootmongo.repository.UserRepository;
import com.springbootmongo.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

import static org.mockito.Mockito.when;

public class UserServiceTest2 {
    @Mock
//    @MockitoBean
    UserRepository repository;
    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Disabled
    @Test
    void findByUserNameTest() {
        when(repository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Krishna").password("krishna").roles(new HashSet<>()).build());
        User user = userService.findByUserName("Krishna");
        Assertions.assertNotNull(user);
    }
}
