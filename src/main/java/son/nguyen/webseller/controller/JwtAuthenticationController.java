package son.nguyen.webseller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import son.nguyen.webseller.config.USER;
import son.nguyen.webseller.config.sercurity.JwtTokenUtil;
import son.nguyen.webseller.model.JwtRequest;
import son.nguyen.webseller.model.JwtResponse;
import son.nguyen.webseller.dto.UserDto;
import son.nguyen.webseller.model.User;
import son.nguyen.webseller.service.JwtUserDetailsService;

@RestController
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
    @RequestMapping(value = "/loginAdmin", method = RequestMethod.POST)
    public ResponseEntity<?> loginAdmin(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        UserDto userDto =userDetailsService.getUserByEmail(authenticationRequest.getEmail());
        if (!userDto.getRole().equals(USER.ROLE_ADMIN)){
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<?> logoutUser( @RequestHeader String Authorization) throws Exception {
        String token =Authorization.substring( 7,Authorization.length());
//        final String token = jwtTokenUtil

        return ResponseEntity.ok(new JwtResponse(token));
    }
    @RequestMapping(value = "/infoUser", method = RequestMethod.GET)
    public ResponseEntity<?> showInfomationUser( @RequestHeader String Authorization) throws Exception {
        String email = jwtTokenUtil.getUsernameFromToken(Authorization);
        UserDto userDto =userDetailsService.getUserByEmail(email);
        return ResponseEntity.ok(userDto);
    }
    @RequestMapping(value = "/checkAdmin", method = RequestMethod.GET)
    public ResponseEntity<?> checkAdminUser( @RequestHeader String Authorization) throws Exception {
        String email = jwtTokenUtil.getUsernameFromToken(Authorization);
        UserDto userDto =userDetailsService.getUserByEmail(email);
        if (!userDto.getRole().equals("ROLE_ADMIN")){
            return ResponseEntity.ok("not admin");
        }
        return ResponseEntity.ok(userDto);
    }
    @PutMapping(value = "updateUserInfo")
    public  ResponseEntity<?> updateUserInfo(@RequestHeader String Authorization,@RequestBody UserDto userDto) {
        String email = jwtTokenUtil.getUsernameFromToken(Authorization);
        User userDtoReturn =userDetailsService.updateUserDao(email,userDto);
        return ResponseEntity.ok(userDtoReturn);
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveRoleUser(@RequestBody UserDto user) throws Exception {
        user.setRole(USER.ROLE_USER.name());
        User userDao =userDetailsService.save(user);
        if (userDao==null)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("email CONFLICT");
        return ResponseEntity.ok(userDao);
    }
    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
