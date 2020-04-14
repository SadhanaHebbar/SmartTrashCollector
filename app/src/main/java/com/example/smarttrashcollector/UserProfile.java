package com.example.smarttrashcollector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class UserProfile {

    public String userName;
    public String userEmail;
    public String userCo;


    public UserProfile(){

    }


    public UserProfile(String userName, String userEmail, String userCo) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userCo = userCo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

       public String getUserCo() {
        return userCo;
    }

    public void setUserCo(String userCo) {
        this.userCo = userCo;
    }
}
