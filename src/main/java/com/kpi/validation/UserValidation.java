package com.kpi.validation;

import com.kpi.dao.UserDao;
import com.kpi.models.User;
import com.kpi.wrapper.UserWrapper;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation implements Validation<UserWrapper> {
    @Override
    public ValidationResult validate(UserWrapper data) {
        if (!isUsernameValid(data.getUsername())){
            return new ValidationResult(false, "Username is not valid or already in use");
        }
        if (!isEmailValid(data.getEmail())){
            return new ValidationResult(false, "Email is not valid or already in use");
        }
        if (!isPhoneNumberValid(data.getPhoneNumber())){
            return new ValidationResult(false, "Phone number is not valid or already in use");
        }
        if (!isPasswordValid(data.getPassword())){
            return new ValidationResult(false, "Password is not strong enough");
        }
        return new ValidationResult(true, "Successful");
    }

    private boolean isUsernameValid(String username){
        if (username == null || username.length() < 5 || username.length() > 20){
            return false;
        }
        ArrayList<User> users = new UserDao().getAll();
        for (User user: users){
            if (user.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }

    private boolean isEmailValid(String email){
        if (email == null){
            return false;
        }
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()){
            return false;
        }
        ArrayList<User> users = new UserDao().getAll();
        for (User user: users){
            if (user.getEmail().equals(email)){
                return false;
            }
        }
        return true;
    }

    private boolean isPhoneNumberValid(String phoneNumber){
        if (phoneNumber == null){
            return false;
        }
        String regex = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()){
            return false;
        }
        ArrayList<User> users = new UserDao().getAll();
        for (User user: users){
            if (user.getPhoneNumber().equals(phoneNumber)){
                return false;
            }
        }
        return true;
    }

    private boolean isPasswordValid(String password){
        if (password == null){
            return false;
        }
        String passwordRegex ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
