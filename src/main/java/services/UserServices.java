package services;






import model.User;

import java.util.List;

public interface UserServices {
    List<User> index();

    User show(Long id);

    void save (User user);

    void delete(Long id);

    void update(User user, Long id);
}
