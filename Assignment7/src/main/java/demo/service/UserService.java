package demo.service;

import demo.dao.UserRepository;
import demo.domain.User;
import demo.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Cacheable(cacheNames = "user", key = "#name",condition = "#name != null")
    public User getUser(String name) throws MyException {
        Optional<User> user = userRepository.findById(name);
        if (user.isEmpty()) {
            throw new MyException("can't find user " + name);
        }
        return user.get();
    }

    @CacheEvict(cacheNames = "user", key = "#name")
    public void deleteUser(String name) throws MyException {
        userRepository.deleteById(name);
    }

    @CacheEvict(cacheNames = "user", key = "#name")
    public User updateUser(String name, User user) throws MyException {
        if (name == null || !name.equals(user.getId()) || userRepository.findById(name).isEmpty()) {
            throw new MyException("can't update user");
        }
        return userRepository.saveAndFlush(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.saveAndFlush(user);
    }



}
