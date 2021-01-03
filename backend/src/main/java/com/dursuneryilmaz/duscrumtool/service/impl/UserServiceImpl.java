package com.dursuneryilmaz.duscrumtool.service.impl;

import com.dursuneryilmaz.duscrumtool.domain.User;
import com.dursuneryilmaz.duscrumtool.exception.ProductIdException;
import com.dursuneryilmaz.duscrumtool.model.response.ExceptionMessages;
import com.dursuneryilmaz.duscrumtool.repository.UserRepository;
import com.dursuneryilmaz.duscrumtool.service.UserService;
import com.dursuneryilmaz.duscrumtool.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    Utils utils;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null)
            // refactor exception messages
            throw new ProductIdException(ExceptionMessages.EMAIL_ALREADY_EXIST.getExceptionMessage());
        user.setUserId(utils.generatePublicId(32));
        // need user dto -> ?
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//      user.setEmailVerificationToken(jetTokenProvider.generateEmailVerificationToken(publicUserId));
        return userRepository.save(user);

    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
        return user;
    }

    @Override
    public User getUserByUserId(String userId) {
        return null;
    }

    @Override
    public User updateUser(String userId, User user) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public List<User> getUsers(int page, int limit) {
        return null;
    }

    @Override
    public boolean verifyEmailToken(String token) {
        return false;
    }

    @Override
    public boolean requestPasswordReset(String email) {
        return false;
    }

    @Override
    public boolean resetPassword(String token, String password) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);
        if (user == null) throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
        return user;
    }
}
