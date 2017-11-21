package gameofthrones.yuriydopa.com.gameofthrones.ui.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import gameofthrones.yuriydopa.com.gameofthrones.R;
import gameofthrones.yuriydopa.com.gameofthrones.data.managers.DataManager;
import gameofthrones.yuriydopa.com.gameofthrones.ui.base.BaseActivity;
import gameofthrones.yuriydopa.com.gameofthrones.ui.main.fragments.TabsFragment;
import gameofthrones.yuriydopa.com.gameofthrones.utils.CircleTransformation;
import gameofthrones.yuriydopa.com.gameofthrones.utils.ConstsManager;

public class MainActivity extends BaseActivity  implements NavigationView.OnNavigationItemSelectedListener, IMainView {

    private static  final String  TAG = ConstsManager.TAG_PREFIX + BaseActivity.class;

    private DataManager mDataManger;
    private MainPresenter presenter;

    @BindView(R.id.drawer_container)DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation_view) NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mDataManger = DataManager.getInstance();
        presenter = MainPresenter.getInsatance();

        setupNavigationMenu();
        showFragmentTab1();
        showFragmentTab2();
        showFragmentTab3();
    }

    private void setupNavigationMenu(){
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setCheckedItem(R.id.nav_item1);
        onNavigationItemSelected(mNavigationView.getMenu().getItem(0));
        ImageView avatar = (ImageView) mNavigationView.getHeaderView(0).findViewById(R.id.avatar);

        mDataManger.getmPicasso().load(R.mipmap.ic_lanister)
                .resizeDimen(R.dimen.size_avatar, R.dimen.size_avatar)
                .onlyScaleDown()
                .centerCrop()
                .transform(new CircleTransformation())
                .into(avatar);
    }

    void setFragment(String houseId) {
        TabsFragment housesTabsFragment;
        FragmentManager fm = getSupportFragmentManager();
        housesTabsFragment = (TabsFragment) fm.findFragmentByTag(TabsFragment.FRAG_TAG);
        if (housesTabsFragment == null){
            housesTabsFragment = new TabsFragment();
        }
        fm.beginTransaction()
                .replace(R.id.main_container, housesTabsFragment, TabsFragment.FRAG_TAG)
                .commit();
       // EventBus.getDefault().post(new ClickItemNavigationMenuEvent(houseId));
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        presenter.onNavigationItemSelected(id);
        return  true;
    }


    @Override
    public void showFragmentTab1() {
        setFragment(ConstsManager.LANNISTER_ID_HOUSE);
    }

    @Override
    public void showFragmentTab2() {
        setFragment(ConstsManager.STARK_ID_HOUSE);
    }

    @Override
    public void showFragmentTab3() {
        setFragment(ConstsManager.TARGARYEN_ID_HOUSE);
    }

    @Override
    public void closeDrawer() {
     mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void showError(String message) {

    }
}
