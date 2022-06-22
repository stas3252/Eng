package project.service;

import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import project.model.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAllUsers();

    User getOneUser(Long id);

    User insertUser(User user);

    //User updateUser(User user, BindingResult bindingResult);

    void deleteUser(Long id);

   String getPage(Model model, HttpSession session, @Nullable Authentication auth);
}
