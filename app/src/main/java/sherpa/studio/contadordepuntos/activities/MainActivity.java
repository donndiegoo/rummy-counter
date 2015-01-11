package sherpa.studio.contadordepuntos.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import info.hoang8f.widget.FButton;
import sherpa.studio.contadordepuntos.R;

public class MainActivity extends Activity {

    FButton mButtonStartGame;
    FButton mButtonStatistics;


    public static void startActivity(Context context) {

        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mButtonStartGame = (FButton) findViewById(R.id.new_game);
        mButtonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayersActivity.startActivity(MainActivity.this);
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        mButtonStatistics = (FButton) findViewById(R.id.statistics);
        mButtonStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatisticsActivity.startActivity(MainActivity.this);
            }
        });
    }
}
