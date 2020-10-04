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
        UserDao newUser = convertDtoToDao(user);
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }
    public UserDto getUserByEmail(String email) {
        UserDao userDao  = userRepository.findByEmail(email);
        UserDto userDto = convertDaoToDto(userDao);
        return userDto;
    }
    private UserDto convertDaoToDto(UserDao userDao){
        UserDto newUser=new UserDto();
        newUser.setEmail(userDao.getEmail());
        newUser.setAddress(userDao.getAddress());
        newUser.setFistName(userDao.getFistName());
        newUser.setLastName(userDao.getLastName());
        newUser.setIdentification(userDao.getIdentification());
        newUser.setUsername(userDao.getUsername());
        newUser.setProvince(userDao.getProvince());
        newUser.setDistrict(userDao.getDistrict());
        newUser.setPhone(userDao.getPhone());
        newUser.setRole(userDao.getRole());
        return newUser;

    }
    private UserDao convertDtoToDao(UserDto userDto){
        UserDao newUser=new UserDao();
        newUser.setEmail(userDto.getEmail());
        newUser.setAddress(userDto.getAddress());
        newUser.setFistName(userDto.getFistName());
        newUser.setLastName(userDto.getLastName());
        newUser.setIdentification(userDto.getIdentification());
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(userDto.getPassword());
        newUser.setProvince(userDto.getProvince());
        newUser.setFistName(userDto.getFistName());
        newUser.setRole(userDto.getRole());
        newUser.setPhone(userDto.getPhone());
        return newUser;

    }
}
