package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;
import web.repository.RoleRepository;
import web.repository.UserRepository;


import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class Init {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public Init(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void InitUsers() {
        if (userRepository.findAll().size() == 0) {
            User userAdmin = new User(
                    "admin",
                    "admin",
                    0,
                    "admin@site.ru",
                    passwordEncoder.encode("admin")
            );
            User userUser = new User(
                    "user",
                    "user",
                    0,
                    "user@site.ru",
                    passwordEncoder.encode("user")
            );
            userRepository.save(userAdmin);
            userRepository.save(userUser);
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleUser = new Role("ROLE_USER");
            List<Role> roles = Arrays.asList(roleAdmin, roleUser);
            roleRepository.saveAll(roles);
            userAdmin.setRoles(Arrays.asList(roleAdmin));
            userUser.setRoles(Arrays.asList(roleUser));
            userRepository.save(userAdmin);
            userRepository.save(userUser);
        }
    }
}
