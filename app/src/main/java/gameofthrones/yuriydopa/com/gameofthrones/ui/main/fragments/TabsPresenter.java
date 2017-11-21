package gameofthrones.yuriydopa.com.gameofthrones.ui.main.fragments;

import gameofthrones.yuriydopa.com.gameofthrones.ui.base.IView;

/**
 * Created by yuriy on 20.02.17.
 */

public class TabsPresenter implements ITabsPresenter {

    private IView mView;

    @Override
    public void takeView(IView view) {
        mView = view;
    }
}
