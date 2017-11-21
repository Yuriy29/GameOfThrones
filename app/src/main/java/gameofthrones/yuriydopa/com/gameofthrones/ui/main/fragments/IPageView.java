package gameofthrones.yuriydopa.com.gameofthrones.ui.main.fragments;

import java.util.List;

import gameofthrones.yuriydopa.com.gameofthrones.data.storages.models.CharacterEntity;
import gameofthrones.yuriydopa.com.gameofthrones.ui.base.IView;

/**
 * Created by yuriy on 20.02.17.
 */

public interface IPageView extends IView {

    void navigateToProfileActivity(long id);

    void setData(List<CharacterEntity> characters);
    void showContent();
}
