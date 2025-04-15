package com.springbootmongo.serviceImpl;

import com.springbootmongo.entity.User;
import com.springbootmongo.repository.UserRepository;
import com.springbootmongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<String> roles = new HashSet<>();
        roles.add("USER");
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, String userName) {
        User dbUser = userRepository.findByUserName(userName);
        if (dbUser != null) {
            dbUser.setUserName(user.getUserName());
            dbUser.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(dbUser);
        }
        return userRepository.save(user);
    }

    @Override
    public boolean deleteUser(String userName) {
        userRepository.deleteByUserName(userName);
        return true;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User adminUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<String> roles = new HashSet<>();
        roles.add("USER");
        roles.add("ADMIN");
        user.setRoles(roles);
        return userRepository.save(user);
    }
}
