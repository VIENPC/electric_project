package com.nhutin.electric_project.repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.nhutin.electric_project.model.ConfirmationCode;
import com.nhutin.electric_project.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT s FROM User s WHERE s.username = ?1")
    User findBytaiKhoanTTTK(String username);

    @Query("SELECT s FROM User s WHERE s.userID = ?1")
    User findByUser(String username);

    User findByEmailLike(String email);

    User findByUserIDLike(int userID);

    User findByusernameLike(String username);

    @Modifying
    @Query("UPDATE User c SET c.password = ?1 WHERE c.userID = ?2")
    void updateUser(String password, int userID);

    @Modifying
    @Query("UPDATE User c SET c.password = ?1 WHERE c.username = ?2")
    void updatepassword(String password, String username);

    Optional<User> findByConfirmationCode(ConfirmationCode confirmationCode);

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM [User] a WHERE a.loginPermission = 1", nativeQuery = true)
    List<User> finAllVer2();

    @Query(value = "SELECT * FROM [User] a WHERE a.FullName like N'%?1%' and a.LoginPermission = 1", nativeQuery = true)
    List<User> finAllLikeName(String name);

    Optional<User> findByUsername(String username);

    Optional<User> findByPhoneNumber(String phoneNumber);

    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.loginPermission = TRUE, registrationDate = ?2 WHERE a.email = ?1")
    int enableUser(String email, Timestamp timestamp);

    // @Query("SELECT kh FROM users kh WHERE kh.registrationDate >= :fiveDaysAgo")
    // List<User> findNewCustomers(Date fiveDaysAgo);
    @Query("SELECT COUNT(u) FROM User u WHERE u.lockStatus = true")
    long countLockedUsers();

}
