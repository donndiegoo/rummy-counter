package sherpa.studio.contadordepuntos.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.GridView;

import com.afollestad.materialdialogs.MaterialDialog;

import info.hoang8f.widget.FButton;
import sherpa.studio.contadordepuntos.Model.Player;
import sherpa.studio.contadordepuntos.MyApplication;
import sherpa.studio.contadordepuntos.R;
import sherpa.studio.contadordepuntos.adapter.PlayersAdapter;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.startActivity(SplashActivity.this);
            }
        }, 3000);


    }
}
