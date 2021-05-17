package com.kpi.sevices;

import com.kpi.models.User;
import com.kpi.wrapper.UserWrapper;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class UserService {
    public static byte[] getPasswordHash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128 );
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = secretKeyFactory.generateSecret(spec).getEncoded();
        return hash;
    }

    public static byte[] getSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static User getUser(String username, String email, String password, String phoneNumber){
        try {
            byte[] salt = getSalt();
            byte[] passwordHash = getPasswordHash(password, salt);
            return new User(username, email, passwordHash, salt, phoneNumber);
        } catch (NoSuchAlgorithmException|InvalidKeySpecException e){
            System.out.println(e.getMessage());
            return new User("", "", new byte[1], new byte[1], phoneNumber);
        }
    }

    public static User getUser(UserWrapper userWrapper){
        try {
            byte[] salt = getSalt();
            byte[] passwordHash = getPasswordHash(userWrapper.getPassword(), salt);
            return new User(userWrapper.getUsername(),
                            userWrapper.getEmail(),
                            passwordHash,
                            salt,
                            userWrapper.getPhoneNumber());
        } catch (NoSuchAlgorithmException|InvalidKeySpecException e){
            System.out.println(e.getMessage());
            return new User("", "", new byte[1], new byte[1], "");
        }
    }
}
