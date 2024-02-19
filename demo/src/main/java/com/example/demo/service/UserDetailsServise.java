package com.example.demo.service;

import com.example.demo.model.DemoUser;
import com.example.demo.model.MyUserDetails;
import com.example.demo.repository.UserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServise implements UserDetailsService {
    private final UserRepo userRepository;

    public UserDetailsServise(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public MyUserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<DemoUser> user = userRepository.findByUserName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        return user.map(MyUserDetails::new).get();

    }

    public DemoUser registerUser(DemoUser user) {
        return userRepository.save(user);
    }

    public Optional<DemoUser> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}




