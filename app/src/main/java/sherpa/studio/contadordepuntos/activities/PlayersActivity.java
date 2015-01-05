package sherpa.studio.contadordepuntos.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.afollestad.materialdialogs.MaterialDialog;

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

                if(MyApplication.mPlayers.getSelectedPlayers().size() < 2)
                {
                    (new MaterialDialog.Builder(PlayersActivity.this)
                            .title("Jugadores")
                            .content("Seleccione al menos 2 jugadores")
                            .neutralText("Ok")
                            .callback(new MaterialDialog.ButtonCallback() {
                                @Override
                                public void onNeutral(MaterialDialog dialog) {
                                    super.onNeutral(dialog);
                                }
                            })).show();
                }
                else
                {
                    GameActivity.startActivity(PlayersActivity.this);
                    finish();
                }

            }
        });

    }
}
