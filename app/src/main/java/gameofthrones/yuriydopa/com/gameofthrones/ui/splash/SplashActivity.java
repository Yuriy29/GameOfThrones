package gameofthrones.yuriydopa.com.gameofthrones.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import gameofthrones.yuriydopa.com.gameofthrones.R;
import gameofthrones.yuriydopa.com.gameofthrones.ui.base.BaseActivity;
import gameofthrones.yuriydopa.com.gameofthrones.ui.main.MainActivity;

public class SplashActivity extends BaseActivity implements ISplashView{

    private ISplashPresenter mPresenter;
    private  boolean mIsDataLoad = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mPresenter = SplasPresenter.getInstace();
        mPresenter.takeView(this);
        mIsDataLoad = mPresenter.loadData();


    }

    @Override
    public void showProgress() {
        super.showProgress();
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
    }

    @Override
    public void showError(String message) {
        super.showToast(message);
    }

    @Override
    public void navigateToMainScreen() {
        if(mIsDataLoad){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
