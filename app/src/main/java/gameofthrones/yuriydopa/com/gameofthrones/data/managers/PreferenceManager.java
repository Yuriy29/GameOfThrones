package gameofthrones.yuriydopa.com.gameofthrones.data.managers;

import android.content.SharedPreferences;

import gameofthrones.yuriydopa.com.gameofthrones.utils.GameOfThronesApplication;

/**
 * Created by yuriy on 19.02.17.
 */

public class PreferenceManager {
    private SharedPreferences mSharedPreferences;

    public PreferenceManager() {
        this.mSharedPreferences = GameOfThronesApplication.getSharedPreferences();
    }
}
