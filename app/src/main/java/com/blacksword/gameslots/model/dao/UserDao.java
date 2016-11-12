package com.blacksword.gameslots.model.dao;

import android.content.SharedPreferences;

/**
 * Created by Zakhar on 09.11.2016.
 */

public interface UserDao {
    public void setCurrentDate(SharedPreferences sharedPreferences, String currentDate);
    public String getAccountDate(SharedPreferences sharedPreferences);
    public float getAccount(SharedPreferences sharedPreferences);
    public void updateAccount(SharedPreferences sharedPreferences, float money);

}
