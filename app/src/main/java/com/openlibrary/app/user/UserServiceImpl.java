package com.openlibrary.app.user;

import com.openlibrary.app.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public UserInfo create(UserCreateRequest userCreateRequest) {
        User requestUser = userConverter.toUser(userCreateRequest);
        return userConverter.fromUser(userRepository.save(requestUser));
    }

    @Override
    public UserInfo get(Long userId) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(ResourceNotFoundException::new);
        return userConverter.fromUser(foundUser);
    }

    @Override
    public void delete(Long userId) {
        userRepository.findById(userId)
                .ifPresentOrElse(
                        user -> userRepository.deleteById(userId),
                        () -> {
                            throw new ResourceNotFoundException();
                        }
                );
    }
}