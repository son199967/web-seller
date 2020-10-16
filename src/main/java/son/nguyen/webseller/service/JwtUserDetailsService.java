package son.nguyen.webseller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import son.nguyen.webseller.dto.UserDto;
import son.nguyen.webseller.model.User;
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
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }


    public User save(UserDto user) {
        User userDao  = userRepository.findByEmail(user.getEmail());
        if (userDao!=null){
            return   null;
        }
        User newUser = convertDtoToDao(user);
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }
    public UserDto getUserByEmail(String email) {
        User userDao  = userRepository.findByEmail(email);
        UserDto userDto = convertDaoToDto(userDao);
        return userDto;
    }
    public User getUseDaorByEmail(String email) {
        User userDao  = userRepository.findByEmail(email);
        return userDao;
    }
    public User updateUserDao(String email,UserDto userDto) {
        User userDao  = userRepository.findByEmail(email);
        userDao.setAddress(userDto.getAddress());
        userDao.setDistrict(userDto.getDistrict());
        userDao.setIdentification(userDto.getIdentification());
        userDao.setFistName(userDto.getFistName());
        userDao.setLastName(userDto.getLastName());
        userDao.setPhone(userDto.getPhone());
        userDao.setProvince(userDto.getProvince());
        userDao.setUsername(userDto.getUsername());
        userRepository.save(userDao);
        return userDao;
    }
    private UserDto convertDaoToDto(User userDao){
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
    private User convertDtoToDao(UserDto userDto){
        User newUser=new User();
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
