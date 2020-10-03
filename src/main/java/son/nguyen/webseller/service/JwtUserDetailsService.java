package son.nguyen.webseller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import son.nguyen.webseller.dto.UserDto;
import son.nguyen.webseller.model.UserDao;
import son.nguyen.webseller.repository.UserRepository;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDao user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }

    public UserDao save(UserDto user) {
        UserDao userDao  = userRepository.findByEmail(user.getEmail());
        if (userDao!=null){
            return   null;
        }
        UserDao newUser = new UserDao();
        newUser.setEmail(user.getEmail());
        newUser.setAddress(user.getAddress());
        newUser.setFistName(user.getFistName());
        newUser.setIdentification(user.getIdentification());
        newUser.setLastName(user.getLastName());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }
}
