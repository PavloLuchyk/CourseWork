package com.kpi.dao;

import com.kpi.models.User;

public interface UserDao extends AbstractDAO<User> {
    void updatePassword(byte[] passwordHash, byte[] passwordSalt, int userId);
    void updateUserDetails(User a);
    void updateAdmin(int id, boolean status);
    void addWithoutPassword(String phoneNumber, String address);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByAddress(String address);
    User getByAddress(String address);
    User getByPhoneNumber(String phoneNumber);
}
