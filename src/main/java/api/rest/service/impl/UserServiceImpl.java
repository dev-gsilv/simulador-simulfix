package api.rest.service.impl;

import api.rest.domain.model.User;
import api.rest.domain.repository.UserRepository;
import api.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.lang.reflect.Field;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User userRequest) {
        Optional<User> existingUser = userRepository.findById(id);
        Class<?> userClass = User.class;
        Field[] fields = userClass.getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object updatedValue = field.get(userRequest);

                if (updatedValue != null) {
                    field.set(existingUser.get(), updatedValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return userRepository.save(existingUser.get());
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserServiceImpl() {
    }
}
