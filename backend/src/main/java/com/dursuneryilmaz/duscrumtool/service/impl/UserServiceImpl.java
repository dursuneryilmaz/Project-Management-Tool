package com.dursuneryilmaz.duscrumtool.service.impl;

import com.dursuneryilmaz.duscrumtool.domain.User;
import com.dursuneryilmaz.duscrumtool.domain.UsersPasswordResetToken;
import com.dursuneryilmaz.duscrumtool.exception.ProductIdException;
import com.dursuneryilmaz.duscrumtool.model.response.ExceptionMessages;
import com.dursuneryilmaz.duscrumtool.repository.UserRepository;
import com.dursuneryilmaz.duscrumtool.repository.UsersPasswordResetTokenRepository;
import com.dursuneryilmaz.duscrumtool.security.jwt.JwtTokenProvider;
import com.dursuneryilmaz.duscrumtool.service.UserService;
import com.dursuneryilmaz.duscrumtool.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    UsersPasswordResetTokenRepository usersPasswordResetTokenRepository;
    @Autowired
    Utils utils;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
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
        user.setEmailVerificationToken(jwtTokenProvider.generateEmailVerificationToken(user.getUserId()));
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
        User user = userRepository.findByUserId(userId);
        if (user == null)
            throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
        return user;
    }

    @Override
    public User updateUser(String userId, User user) {
        User userEntity = userRepository.findByUserId(userId);
        if (userEntity == null)
            throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        return userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null)
            throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
        userRepository.delete(user);
    }

    // user repo is a jpa repo, is it extends paging and sorting repo -> ?
    @Override
    public List<User> getUsers(int page, int limit) {
        if (page > 0) page -= 1;
        Pageable pageRequest = PageRequest.of(page, limit);
        Page<User> userPages = userRepository.findAll(pageRequest);
        return userPages.getContent();

    }

    @Override
    public boolean verifyEmailToken(String token) {
        boolean isVerified = false;
        String userId = jwtTokenProvider.getUserIdFromEmailVerificationToken(token);
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            boolean hasTokenExpired = jwtTokenProvider.hasEmailVerificationTokenExpired(token);
            if (!hasTokenExpired) {
                user.setEmailVerificationToken(null);
                user.setEmailVerificationStatus(Boolean.TRUE);
                userRepository.save(user);
                isVerified = true;
            }
        }
        return isVerified;
    }

    @Override
    public boolean requestPasswordReset(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) return false;
        String token = jwtTokenProvider.generatePasswordResetToken(user.getUserId());

        UsersPasswordResetToken usersPasswordResetToken = new UsersPasswordResetToken();
        usersPasswordResetToken.setToken(token);
        usersPasswordResetToken.setUser(user);
        usersPasswordResetTokenRepository.save(usersPasswordResetToken);
        //isEmailSent = gmailService.sendPasswordResetMail(user.getEmail(), user.getFirstName(), token);
        //return isEmailSent;
        return true;
    }

    @Override
    public boolean resetPassword(String token, String password) {
        // if token expired
        if (jwtTokenProvider.hasPasswordResetTokenExpired(token)) return false;

        // if token not found in db
        UsersPasswordResetToken usersPasswordResetToken = usersPasswordResetTokenRepository.findByToken(token);
        if (usersPasswordResetToken == null) return false;

        // encode new password
        String encodedPassword = bCryptPasswordEncoder.encode(password);

        // Update User's password in database
        User user = usersPasswordResetToken.getUser();
        user.setEncryptedPassword(encodedPassword);
        User savedUserEntity = userRepository.save(user);

        // Verify if password was saved successfully
        if (savedUserEntity.getPassword().equals(encodedPassword)) {
            // Remove Password Reset token from database
            usersPasswordResetTokenRepository.delete(usersPasswordResetToken);
            return true;
        }
        return false;

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);
        if (user == null) throw new ProductIdException(ExceptionMessages.NO_RECORD_FOUND.getExceptionMessage());
        return user;
    }
}
