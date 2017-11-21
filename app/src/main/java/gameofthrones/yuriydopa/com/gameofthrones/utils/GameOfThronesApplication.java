package gameofthrones.yuriydopa.com.gameofthrones.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.greenrobot.greendao.database.Database;

import gameofthrones.yuriydopa.com.gameofthrones.data.storages.models.DaoMaster;
import gameofthrones.yuriydopa.com.gameofthrones.data.storages.models.DaoSession;

/**
 * Created by yuriy on 19.02.17.
 */

public class GameOfThronesApplication extends Application {
    private static SharedPreferences sharedPreferences;
    private static Context sContext;
    private static DaoSession sDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sContext = getApplicationContext();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "GameOfThrones-db");
        Database db = helper.getWritableDb();
        sDaoSession = new DaoMaster(db).newSession();
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static Context getContext() {
        return sContext;
    }

    public static DaoSession getsDaoSession() {
        return sDaoSession;
    }
}
