package gameofthrones.yuriydopa.com.gameofthrones.ui.main.fragments;

import gameofthrones.yuriydopa.com.gameofthrones.ui.base.IPresenter;

/**
 * Created by yuriy on 20.02.17.
 */

public interface IPagePresenter extends IPresenter {
    void clickByItemListView(long id);
    void loadDataByHouse(String houseId);
}
