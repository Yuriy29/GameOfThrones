package gameofthrones.yuriydopa.com.gameofthrones.ui.main.fragments;

import android.os.Handler;

import org.greenrobot.greendao.async.AsyncOperation;
import org.greenrobot.greendao.async.AsyncOperationListener;
import org.greenrobot.greendao.async.AsyncSession;

import java.util.ArrayList;
import java.util.List;

import gameofthrones.yuriydopa.com.gameofthrones.data.managers.DataManager;
import gameofthrones.yuriydopa.com.gameofthrones.data.storages.models.CharacterEntity;
import gameofthrones.yuriydopa.com.gameofthrones.data.storages.models.DaoSession;
import gameofthrones.yuriydopa.com.gameofthrones.ui.base.IView;
import gameofthrones.yuriydopa.com.gameofthrones.utils.ConstsManager;

/**
 * Created by yuriy on 20.02.17.
 */

public class PagePresenter implements IPagePresenter {

    private IPageView mView;
    private static final String TAG = ConstsManager.TAG_PREFIX + "MembersPresenter";

    private String houseId;
    private Handler mHandler;
    private DataManager mDataManager;

    private List<CharacterEntity> mData;

    private PagePresenter() {
        //no instance
        this.mHandler = new Handler();
        mDataManager = DataManager.getInstance();
    }

    public static PagePresenter getInstance() {
        return new PagePresenter();
    }

    @Override
    public void clickByItemListView(long id) {
        mView.navigateToProfileActivity(id);

    }

    @Override
    public void loadDataByHouse(final String houseId) {
        this.houseId = houseId;
        if (mData == null) {
            AsyncSession session = mDataManager.getDaoSession().startAsyncSession();
            session.setListener(new AsyncOperationListener() {
                @Override
                public void onAsyncOperationCompleted(AsyncOperation operation) {
                    mData = (List<CharacterEntity>) operation.getResult();
                    setData(houseId);
                }
            });
            session.loadAll(CharacterEntity.class);
        } else {
            setData(houseId);
        }
    }

    private void setData(final String hId) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<CharacterEntity> data = new ArrayList<CharacterEntity>();
                for(CharacterEntity entity : mData){
                    if(entity.getRemoteHouseId().equals(hId)){
                        data.add(entity);
                    }
                }
                mView.setData(data);
                mView.showContent();
            }
        }, 2000);
    }

    @Override
    public void takeView(IView view) {
        mView = (IPageView) view;
    }
}
