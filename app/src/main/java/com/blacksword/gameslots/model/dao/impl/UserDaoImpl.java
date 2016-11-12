package com.blacksword.gameslots.model.dao.impl;

import android.content.SharedPreferences;
import com.blacksword.gameslots.model.dao.UserDao;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import static com.blacksword.gameslots.model.constants.Constants.*;

/**
 * Created by Zakhar on 09.11.2016.
 */

public class UserDaoImpl implements UserDao {
    private SharedPreferences sharedPreferences;

    /**
     * updates user's account
     * @param money
     */
    public void updateAccount(SharedPreferences sharedPreferences, float money){
        money += getAccount(sharedPreferences);
        //sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(ACCOUNT_DATA, money);
        editor.commit();
    }

    /**
     * updates user's account (every day)
     */
    public void updateAccount(SharedPreferences sharedPreferences){
        if(getAccountDate(sharedPreferences).equals(EMPTY_STRING)){
            updateAccount(sharedPreferences, START_MONEY);
        }
        String currentDate = getCurrentDate();
        if(!getAccountDate(sharedPreferences).equals(currentDate)) {
            updateAccount(sharedPreferences, DAILY_MONEY);
            setCurrentDate(sharedPreferences, currentDate);
        }
    }

    /**
     * returns current account
     * @return
     */
    public float getAccount(SharedPreferences sharedPreferences){
        //sharedPreferences = getPreferences(MODE_PRIVATE);
        float resultAccount = sharedPreferences.getFloat(ACCOUNT_DATA, 0);
        return resultAccount;
    }

    /**
     * returns current date without time (String)
     * @return current date (String)
     */
    public String getCurrentDate(){
        Locale local = new Locale("uk","UA");
        DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, local);
        Date currentDate = new Date();
        return df.format(currentDate).toString();
    }

    /**
     * returns last updating date (String)
     * @return date (String)
     */
    public String getAccountDate(SharedPreferences sharedPreferences){
        //sharedPreferences = getPreferences(MODE_PRIVATE);
        String resultDate = sharedPreferences.getString(DATE_DATA, "");
        return resultDate;
    }

    /**
     * sets current date to the preference
     * @param currentDate
     */
    public void setCurrentDate(SharedPreferences sharedPreferences, String currentDate){
        //sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DATE_DATA, currentDate);
        editor.commit();
    }
}
