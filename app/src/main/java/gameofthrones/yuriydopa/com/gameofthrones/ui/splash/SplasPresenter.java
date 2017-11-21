package gameofthrones.yuriydopa.com.gameofthrones.ui.splash;

import android.content.Context;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gameofthrones.yuriydopa.com.gameofthrones.data.managers.DataManager;
import gameofthrones.yuriydopa.com.gameofthrones.data.network.NetworkStatusChecker;
import gameofthrones.yuriydopa.com.gameofthrones.data.network.responce.CharactertResp;
import gameofthrones.yuriydopa.com.gameofthrones.data.network.responce.HouseListResp;
import gameofthrones.yuriydopa.com.gameofthrones.data.storages.models.AliasEntity;
import gameofthrones.yuriydopa.com.gameofthrones.data.storages.models.CharacterEntity;
import gameofthrones.yuriydopa.com.gameofthrones.data.storages.models.TitleEntity;
import gameofthrones.yuriydopa.com.gameofthrones.ui.base.IView;
import gameofthrones.yuriydopa.com.gameofthrones.utils.GameOfThronesApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static gameofthrones.yuriydopa.com.gameofthrones.utils.ConstsManager.LANNISTER_ID_HOUSE;
import static gameofthrones.yuriydopa.com.gameofthrones.utils.ConstsManager.STARK_ID_HOUSE;
import static gameofthrones.yuriydopa.com.gameofthrones.utils.ConstsManager.TARGARYEN_ID_HOUSE;
import static gameofthrones.yuriydopa.com.gameofthrones.utils.EntityUtils.cutIdFromUrl;

/**
 * Created by yuriy on 20.02.17.
 */

public class SplasPresenter implements ISplashPresenter {
    private IView mView;
    private Handler handler = new Handler();
    private DataManager mDataManager;
    private Context mContext;
    private static ISplashPresenter INSTANCE = new SplasPresenter();

    private List<CharacterEntity> mMemberEntityList = new ArrayList<>();
    private List<TitleEntity> mTitleEntityList = new ArrayList<>();
    private List<AliasEntity> mAliasEntityList = new ArrayList<>();

    private static final List<String> houseIds = Arrays
            .asList(STARK_ID_HOUSE, LANNISTER_ID_HOUSE, TARGARYEN_ID_HOUSE);

    private SplasPresenter() {
        //no instance
        mDataManager = DataManager.getInstance();
        mContext = GameOfThronesApplication.getContext();
    }

    public static ISplashPresenter getInstace() {
        return INSTANCE;
    }

    @Override
    public void takeView(IView view) {
        mView = view;
    }

    @Override
    public boolean loadData() {
        mView.showProgress();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startLoad();
            }
        }, 4000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onFinishedLoadData();
            }
        }, 10000);
        return true;
    }

    private void startLoad() {
        if (NetworkStatusChecker.isNetworkAvailable(mContext)) {
            for (String houseId : houseIds) {
                loadHouseList(houseId);
            }
        } else {
           // showSnackBar("Network is not avaiable!");
        }
    }

    private void loadHouseList(final String houseId){
        Call<HouseListResp> call = mDataManager.getHouseLisFromNetwork(houseId);
        call.enqueue(new Callback<HouseListResp>() {
            @Override
            public void onResponse(Call<HouseListResp> call, Response<HouseListResp> response) {
                if (response.code() == 200) {
                    loadCharacterListByHouse(response.body().getSwornMembers(),response.body(),houseId);
                }

            }

            @Override
            public void onFailure(Call<HouseListResp> call, Throwable t) {

            }
        });
    }

    private void loadCharacterListByHouse(List<String> characters, final HouseListResp houseResp, final String houseId){
        for(String id : characters) {
            id = cutIdFromUrl(id);
            Call<CharactertResp> call = mDataManager.getCharactersListFromNetwork(id);
            call.enqueue(new Callback<CharactertResp>() {
                @Override
                public void onResponse(Call<CharactertResp> call, Response<CharactertResp> response) {
                    if (response.code() == 200) {
                        CharacterEntity memberEntity = new CharacterEntity(response.body(), cutIdFromUrl(houseResp.getUrl()), houseResp.getWords());
                        mMemberEntityList.add(memberEntity);

                        List<TitleEntity> titles = new ArrayList<>(response.body().getTitles().size());
                        for (String title: response.body().getTitles()) titles.add(new TitleEntity(title, memberEntity.getRemoteId()));
                        mTitleEntityList.addAll(titles);

                        List<AliasEntity> aliases = new ArrayList<>(response.body().getAliases().size());
                        for (String alias: response.body().getAliases()) aliases.add(new AliasEntity(alias, memberEntity.getRemoteId()));
                        mAliasEntityList.addAll(aliases);
                    }
                }

                @Override
                public void onFailure(Call<CharactertResp> call, Throwable t) {

                }
            });
        }
    }

    private void onFinishedLoadData() {
        saveAllMembersTx();
        mView.hideProgress();
        ((ISplashView) mView).navigateToMainScreen();
    }

    private void saveAllMembersTx() {
        mDataManager.getDaoSession().getCharacterEntityDao().insertOrReplaceInTx(mMemberEntityList);
        mDataManager.getDaoSession().getTitleEntityDao().insertOrReplaceInTx(mTitleEntityList);
        mDataManager.getDaoSession().getAliasEntityDao().insertOrReplaceInTx(mAliasEntityList);

    }
}
