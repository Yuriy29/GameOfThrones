package gameofthrones.yuriydopa.com.gameofthrones.ui.base;

/**
 * Created by yuriy on 20.02.17.
 */

public interface IView {
    void showProgress();
    void hideProgress();
    void showError(String message);
}
