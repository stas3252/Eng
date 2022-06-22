package project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.model.User;
import project.repository.UserRepository;
import project.service.UserService;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getOneUser(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User insertUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByEmail(s)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", s))
                );
    }

    @Override
    public String getPage(Model model, HttpSession session, Authentication auth) {
        if (Objects.isNull(auth)) {
            model.addAttribute("authenticatedName", session.getAttribute("authenticatedName"));
            session.removeAttribute("authenticatedName");

            model.addAttribute("authenticationException", session.getAttribute("authenticationException"));
            session.removeAttribute("authenticationException");

            return "login-page";
        }
        User user = (User) auth.getPrincipal();
        model.addAttribute("user", user);

        if (user.hasRole("ROLE_ADMIN")) {
            return "admin-page";
        }

        if (user.hasRole("ROLE_USER")) {
            return "user-page";
        }

        return "access-denied-page";

    }
}
