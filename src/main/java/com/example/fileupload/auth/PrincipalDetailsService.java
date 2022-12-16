package com.example.fileupload.auth;

import com.example.fileupload.user.dao.UserRepository;
import com.example.fileupload.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("받은 username: " + username);
        User user = userRepository.findByUsername(username);
        System.out.println("user: " + user);

        // 문제 추정 부분
        return new PrincipalDetails(user);

    }
}
