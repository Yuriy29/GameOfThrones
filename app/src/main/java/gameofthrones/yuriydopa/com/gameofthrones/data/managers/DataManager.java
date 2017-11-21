package gameofthrones.yuriydopa.com.gameofthrones.data.managers;

import android.content.Context;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import gameofthrones.yuriydopa.com.gameofthrones.data.network.PicassoCache;
import gameofthrones.yuriydopa.com.gameofthrones.data.network.RestService;
import gameofthrones.yuriydopa.com.gameofthrones.data.network.ServiceGenerator;
import gameofthrones.yuriydopa.com.gameofthrones.data.network.responce.CharactertResp;
import gameofthrones.yuriydopa.com.gameofthrones.data.network.responce.HouseListResp;
import gameofthrones.yuriydopa.com.gameofthrones.data.storages.models.CharacterEntity;
import gameofthrones.yuriydopa.com.gameofthrones.data.storages.models.DaoSession;
import gameofthrones.yuriydopa.com.gameofthrones.data.storages.models.TitleEntity;
import gameofthrones.yuriydopa.com.gameofthrones.data.storages.models.TitleEntityDao;
import gameofthrones.yuriydopa.com.gameofthrones.utils.GameOfThronesApplication;
import retrofit2.Call;

/**
 * Created by yuriy on 19.02.17.
 */

public class DataManager {
    public static DataManager INSTANCE = null;
    private PreferenceManager preferenceManager;
    private Context mContext;
    private RestService mRestService;
    private Picasso mPicasso;
    private DaoSession mDaoSession;


    private DataManager() {
        //no instance
        preferenceManager = new PreferenceManager();
        mContext = GameOfThronesApplication.getContext();
        mRestService = ServiceGenerator.createService(RestService.class);
        mPicasso = new PicassoCache(mContext).getPicassoInstance();
        mDaoSession = GameOfThronesApplication.getsDaoSession();
    }

    public static DataManager getInstance() {
        if(INSTANCE == null){
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    public PreferenceManager getPreferenceManager() {
        return preferenceManager;
    }

    public Picasso getmPicasso() {
        return mPicasso;
    }

    public RestService getRestService() {
        return mRestService;
    }

    public Call<HouseListResp> getHouseLisFromNetwork(String id){
        return mRestService.getHose(id);
    }

    public Call<CharactertResp> getCharactersListFromNetwork(String id){
        return mRestService.getCharacters(id);
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public List<TitleEntity> getHouseListFromDb(){
        List<TitleEntity> userList = new ArrayList<>();

        try {
            userList = mDaoSession.queryBuilder(TitleEntity.class)
                    .where(TitleEntityDao.Properties.MemberRemoteId.gt(0))
                    .orderDesc(TitleEntityDao.Properties.Title)
                    .build()
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public List<CharacterEntity> getCharacterListFromDb(){
        List<CharacterEntity> characterList = new ArrayList<>();

        try {
            characterList = mDaoSession.queryBuilder(CharacterEntity.class)
                    .where(TitleEntityDao.Properties.MemberRemoteId.gt(0))
                    .orderDesc(TitleEntityDao.Properties.Title)
                    .build()
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return characterList;
    }
}
