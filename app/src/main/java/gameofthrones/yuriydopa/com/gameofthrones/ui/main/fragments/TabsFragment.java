package gameofthrones.yuriydopa.com.gameofthrones.ui.main.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import gameofthrones.yuriydopa.com.gameofthrones.R;
import gameofthrones.yuriydopa.com.gameofthrones.ui.adapters.TabsAdapter;
import gameofthrones.yuriydopa.com.gameofthrones.utils.ConstsManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabsFragment extends Fragment implements ITabsView {

    public static final String FRAG_TAG = "HousesTabs_FRAG_TAG";

    private TabsPresenter mPresenter;
    private Unbinder unbinder;

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.tabs ) TabLayout mTab;
    @BindView(R.id.view_pager) ViewPager mPager;


    public TabsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tabs, container, false);
        unbinder = ButterKnife.bind(this, view);
        mPager.setOffscreenPageLimit(3);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.setSupportActionBar(mToolbar);
        DrawerLayout drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_container);
        setupDrawer(activity, drawerLayout);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewPager();
        mTab.setupWithViewPager(mPager);
    }

    private void setupViewPager() {
        TabsAdapter adapter = new TabsAdapter(getChildFragmentManager());
        adapter.addFragment(new PageFragment(), ConstsManager.STARK_ID_HOUSE, getString(R.string.house_item1));
        adapter.addFragment(new PageFragment(), ConstsManager.LANNISTER_ID_HOUSE, getString(R.string.house_item2));
        adapter.addFragment(new PageFragment(), ConstsManager.TARGARYEN_ID_HOUSE, getString(R.string.house_item3));
        mPager.setAdapter(adapter);
    }

    private void setupDrawer(AppCompatActivity activity, DrawerLayout drawerLayout) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, drawerLayout, mToolbar,
                R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onStop() {
        super.onStop();
        unbinder.unbind();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void setSelectedTab() {

    }
}
