package com.kpi.models;

import java.sql.Timestamp;
import java.util.Objects;

public class User {
    private int userId;
    private String username;
    private String email;
    private byte[] passwordHash;
    private byte[] passwordSalt;
    private String phoneNumber;
    private Timestamp creationTime;
    private boolean admin;
    private String address;

    public User(int userId, String username, String email, byte[] passwordHash, byte[] passwordSalt, String phoneNumber, Timestamp creationTime, boolean admin, String address) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.phoneNumber = phoneNumber;
        this.creationTime = creationTime;
        this.admin = admin;
        this.address = address;
    }

    public User(String username, String email, byte[] passwordHash, byte[] passwordSalt, String phoneNumber, Timestamp creationTime) {
        userId = 0;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.phoneNumber = phoneNumber;
        this.creationTime = creationTime;
    }

    public User(String username, String email, byte[] passwordHash, byte[] passwordSalt, String phoneNumber, String address) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public User(int userId ,String username, String email, String phoneNumber) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    public byte[] getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(byte[] passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, email, phoneNumber);
    }
}
