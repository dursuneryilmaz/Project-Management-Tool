package com.dursuneryilmaz.duscrumtool.repository;

import com.dursuneryilmaz.duscrumtool.domain.UsersPasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersPasswordResetTokenRepository extends JpaRepository<UsersPasswordResetToken, Long> {
    UsersPasswordResetToken findByToken(String token);
}
