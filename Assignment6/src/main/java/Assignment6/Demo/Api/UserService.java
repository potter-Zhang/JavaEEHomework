package Assignment6.Demo.Api;

import Assignment6.Demo.Domain.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    ArrayList<User> users = new ArrayList<User>();

    public void addUser(User user) throws Exception {

        users.add(user);
    }

    public User getUser(int idx) throws Exception {
        return users.get(idx);
    }

    public Long sumAge() throws Exception {
        Long sum = 0L;
        for (User user : users) {
            sum += user.getAge();
        }
        return sum;
    }

    public void deleteUser(User user) throws Exception {
        users.remove(user);
    }
}
