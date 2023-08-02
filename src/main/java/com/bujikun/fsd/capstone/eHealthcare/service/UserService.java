package com.bujikun.fsd.capstone.eHealthcare.service;

import com.bujikun.fsd.capstone.eHealthcare.config.dto.UserDTO;
import com.bujikun.fsd.capstone.eHealthcare.entity.Permission;
import com.bujikun.fsd.capstone.eHealthcare.entity.User;
import com.bujikun.fsd.capstone.eHealthcare.exceptions.user.UserExistsException;
import com.bujikun.fsd.capstone.eHealthcare.exceptions.user.UserNotFoundException;
import com.bujikun.fsd.capstone.eHealthcare.repository.PermissionRepository;
import com.bujikun.fsd.capstone.eHealthcare.repository.UserRepository;
import com.bujikun.fsd.capstone.eHealthcare.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements IBaseService<User, UUID> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DateUtil util;
    private final PermissionRepository permissionRepository;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(UUID uuid) {

         return userRepository.findById(uuid);
    }
    public UserDTO findOneById(UUID uuid) {
        var u = findById(uuid).orElseThrow(()-> new UserNotFoundException("User Not Found"));
        return UserDTO.fromUser(u,util);
    }
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(()-> new UserNotFoundException("User Not Found"));

    }
    @Override
    public User save(User user) {
        if(userRepository.existsByUsername(user.getUsername())){
            throw new UserExistsException("Username already taken!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsAccountExpired(false);
        user.setIsEnabled(true);
        user.setIsCredentialsExpired(false);
        user.setIsAccountLocked(false);
        user.setDeleted(false);
        user.setCreatedOn(util.now());
        user.linkPermission(permissionRepository.findByName("USER"));
        return userRepository.save(user);
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public Integer getCount() {
        return userRepository.getCount();
    }
}
