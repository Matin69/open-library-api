package com.openlibrary.app.user;

import com.openlibrary.app.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserInfo create(UserCreateRequest userCreateRequest) {
        User requestUser = UserConverter.toUser(userCreateRequest);
        return UserConverter.fromUser(userRepository.save(requestUser));
    }

    @Override
    public UserInfo get(Long userId) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(ResourceNotFoundException::new);
        return UserConverter.fromUser(foundUser);
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
