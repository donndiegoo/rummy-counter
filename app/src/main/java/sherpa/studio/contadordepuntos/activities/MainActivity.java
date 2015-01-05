package sherpa.studio.contadordepuntos.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import info.hoang8f.widget.FButton;
import sherpa.studio.contadordepuntos.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FButton buttonStartGame = (FButton) findViewById(R.id.new_game);
        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayersActivity.startActivity(MainActivity.this);
            }
        });

        FButton buttonStatistics = (FButton) findViewById(R.id.statistics);
        buttonStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatisticsActivity.startActivity(MainActivity.this);
            }
        });
    }
}
