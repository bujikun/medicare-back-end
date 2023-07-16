package com.bujikun.fsd.capstone.eHealthcare.security.service;

import com.bujikun.fsd.capstone.eHealthcare.security.repository.SecurityUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SecurityUserDetailsService implements UserDetailsService {
    private final SecurityUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var ud = userRepository.loadUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid user"));
        return ud;
    }
}
