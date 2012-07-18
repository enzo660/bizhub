package com.bizhub.bzwebapp.web;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.bizhub.bzwebapp.Util;
import com.bizhub.bzwebapp.domain.User;

@PasswordRequiredForNewUsers
@VerifiedPassword
public class UserAndPassword implements Serializable {

    private static final long serialVersionUID = -955350814749881282L;

    private final User user;

    private String password;

    private String passwordVerification;

    public UserAndPassword(User user) {
        this.user = user;
    }

    @Valid
    @NotNull
    public User getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordVerification() {
        return this.passwordVerification;
    }

    public void setPasswordVerification(String passwordVerification) {
        this.passwordVerification = passwordVerification;
    }

    public boolean isPasswordVerificationSet() {
        return !Util.isEmpty(this.passwordVerification);
    }

    public boolean isPasswordSet() {
        return !Util.isEmpty(this.password);
    }

    public boolean isPasswordVerified() {
        return this.isPasswordSet()
                && this.password.equals(this.passwordVerification);
    }
}