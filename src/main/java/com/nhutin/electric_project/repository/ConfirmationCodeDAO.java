package com.nhutin.electric_project.repository;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.nhutin.electric_project.model.ConfirmationCode;

public interface ConfirmationCodeDAO extends JpaRepository<ConfirmationCode, Integer> {
    Optional<ConfirmationCode> findByOTPCode(String oTPCode);

    Optional<ConfirmationCode> findByUserID(int userID);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationCode c SET c.OTPCode = ?1 WHERE c.userID = ?2")
    int updateConfirmationCode(String newToken, int userId);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationCode c " +
                    "SET c.isConfirmed = ?2 " +
                    "WHERE c.OTPCode = ?1")
    int updateConfirmed(String token,
                    Boolean isConfirm);

    @Modifying
    @Query("UPDATE ConfirmationCode c SET c.OTPCode = ?1, c.isConfirmed = ?2, c.OTPCreationDate = ?3, c.OTPExpirationDate = ?4 WHERE c.userID = ?5")
    void updateCodeOTP(String OTPCode, boolean isConfirmed, Timestamp OTPCreationDate, Timestamp OTPExpirationDate,
                    int userID);

    @Modifying
    @Query("UPDATE ConfirmationCode c SET c.isConfirmed = ?1 WHERE c.userID = ?2")
    void updateIsConfirmed(boolean isConfirmed, int userID);

    // @Modifying
    // @Query("INSERT INTO ConfirmationCode (userID, OTPCode, isConfirmed,
    // OTPCreationDate, OTPExpirationDate) VALUES (?1, ?2, ?3, ?4, ?5)")
    // void createCodeOTP(int userID, String OTPCode, boolean isConfirmed, Timestamp
    // OTPCreationDate, Timestamp OTPExpirationDate);

    ConfirmationCode findByUserIDLike(int userID);
}

