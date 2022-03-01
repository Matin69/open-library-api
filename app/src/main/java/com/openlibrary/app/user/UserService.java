package com.openlibrary.app.user;

import com.openlibrary.app.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public UserInfo create(UserCreateRequest userCreateRequest) {
        User requestUser = userConverter.toUser(userCreateRequest);
        return userConverter.fromUser(userRepository.save(requestUser));
    }

    public UserInfo get(Long userId) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(ResourceNotFoundException::new);
        return userConverter.fromUser(foundUser);
    }

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}
