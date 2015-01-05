package sherpa.studio.contadordepuntos.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import info.hoang8f.widget.FButton;
import sherpa.studio.contadordepuntos.Model.Player;
import sherpa.studio.contadordepuntos.MyApplication;
import sherpa.studio.contadordepuntos.R;
import sherpa.studio.contadordepuntos.adapter.PlayersAdapter;


public class PlayersActivity extends ActionBarActivity {

    private GridView mGridView;
    private PlayersAdapter mAdapter;

    public static void startActivity(Context context)
    {
        Intent intent = new Intent(context, PlayersActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        for(Player player : MyApplication.mPlayers.getPlayers())
        {
            player.setSelected(false);
        }

        mGridView = (GridView) findViewById(R.id.gridview);
        mAdapter = new PlayersAdapter(this, MyApplication.mPlayers.getPlayers());
        mGridView.setAdapter(mAdapter);

        FButton buttonStartGame = (FButton) findViewById(R.id.start_game);
        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameActivity.startActivity(PlayersActivity.this);
                finish();
            }
        });

    }
}
