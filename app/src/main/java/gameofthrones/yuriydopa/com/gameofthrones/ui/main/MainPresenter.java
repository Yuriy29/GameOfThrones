package gameofthrones.yuriydopa.com.gameofthrones.ui.main;

import gameofthrones.yuriydopa.com.gameofthrones.ui.base.IPresenter;
import gameofthrones.yuriydopa.com.gameofthrones.ui.base.IView;
import gameofthrones.yuriydopa.com.gameofthrones.ui.base.Model;

/**
 * Created by yuriy on 20.02.17.
 */

public class MainPresenter implements IMainPresenter {

    private static final MainPresenter pInsatance = new MainPresenter();
    private IView mView;
    private Model mModel;

    private MainPresenter() {
        //no instance
        mModel = new Model();
    }

    public static MainPresenter getInsatance() {
        return pInsatance;
    }

    @Override
    public void takeView(IView view) {
        mView = view;
    }

    @Override
    public void onNavigationItemSelected(int itemId) {

    }
}
