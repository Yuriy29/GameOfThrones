package gameofthrones.yuriydopa.com.gameofthrones.ui.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import gameofthrones.yuriydopa.com.gameofthrones.utils.ConstsManager;

/**
 * Created by yuriy on 20.02.17.
 */

public class TabsAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragments = new ArrayList<>(3);
    private final List<String> mFragmentTitles = new ArrayList<>(3);

    public TabsAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public void addFragment(Fragment fragment, String houseId, String title) {
        Bundle args = new Bundle();
        args.putString(ConstsManager.KEY_HOUSE_ID, houseId);
        fragment.setArguments(args);
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }
}
