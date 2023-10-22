package api.rest.service;

import api.rest.domain.model.User;


public interface UserService {
    Iterable<User> findAll();

    User findById(Long userId);

    User create(User user);

    User update(Long id, User user);

    void delete(Long id);

}
