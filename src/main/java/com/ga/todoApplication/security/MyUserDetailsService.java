package com.ga.todoApplication.security;


import com.ga.todoApplication.model.User;
import com.ga.todoApplication.repository.UserRepository;
import com.ga.todoApplication.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String emailAddress)
            throws UsernameNotFoundException {

        User user = userRepository.findUserByEmailAddress(emailAddress);

        if (user == null) {
            throw new UsernameNotFoundException(
                    "User not found with email: " + emailAddress
            );
        }

        return new MyUserDetails(user);
    }
}
