package gameofthrones.yuriydopa.com.gameofthrones.ui.characterprofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import gameofthrones.yuriydopa.com.gameofthrones.R;
import gameofthrones.yuriydopa.com.gameofthrones.ui.base.BaseActivity;

public class CharacterDetailsActivity extends BaseActivity {

    public static final String KEY_MEMBER_DETAILS_ID = "KEY_MEMBER_DETAILS_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);
    }
}
