package com.example.library.User;

enum Gender{
    male, female
}

public class User {
    private String username;
    private String realname;
    private String password;
    private Gender gender;

    public User(String username, String realname, String password, Gender gender) {
        this.username = username;
        this.realname = realname;
        this.password = password;
        this.gender = gender;
    }

    public User() { }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User [gender=" + gender + ", password=" + password + ", realname=" + realname + ", username="
                + username + "]";
    }


}