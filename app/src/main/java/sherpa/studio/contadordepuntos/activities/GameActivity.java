package sherpa.studio.contadordepuntos.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import sherpa.studio.contadordepuntos.Model.Game;
import sherpa.studio.contadordepuntos.Model.Player;
import sherpa.studio.contadordepuntos.Model.PlayerGame;
import sherpa.studio.contadordepuntos.MyApplication;
import sherpa.studio.contadordepuntos.R;
import sherpa.studio.contadordepuntos.fragments.PlayerGameFragment;


public class GameActivity extends ActionBarActivity {

    private PlayerGameFragment mFragmentPlayer1;
    private PlayerGameFragment mFragmentPlayer2;
    private PlayerGameFragment mFragmentPlayer3;
    private PlayerGameFragment mFragmentPlayer4;
    private PlayerGameFragment mFragmentPlayer5;
    private PlayerGameFragment mFragmentPlayer6;

    private LinearLayout mPlayersContainer;
    private View mPlayer1Container;
    private View mPlayer2Container;
    private View mPlayer3Container;
    private View mPlayer4Container;
    private View mPlayer5Container;
    private View mPlayer6Container;

    private Game         mGame;
    private List<Player> mListPlayers;
    private List<PlayerGame> mListPlayerGames;

    public static void startActivity(Context context)
    {
        Intent intent = new Intent(context, GameActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mListPlayers = MyApplication.mPlayers.getSelectedPlayers();
        mListPlayerGames = new ArrayList<>();

        mGame = new Game(String.valueOf(Calendar.getInstance(Locale.FRANCE).getTimeInMillis()), mListPlayerGames);

        configurePlayers();

        findViewById(R.id.finish_game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (new MaterialDialog.Builder(GameActivity.this)
                        .title("Partida terminada")
                        .content("?Desea terminar la partida?")
                        .positiveText("Si")
                        .negativeText("No")
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                super.onPositive(dialog);

                                MyApplication.addGame(mGame);
                                finish();
                                //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);


                            }

                            @Override
                            public void onNegative(MaterialDialog dialog) {
                                super.onNegative(dialog);
                            }
                        })).show();
            }
        });
    }

    private void configurePlayers() {

        mPlayersContainer = (LinearLayout) findViewById(R.id.players_container);
        mPlayer1Container = findViewById(R.id.player_1);
        mPlayer2Container = findViewById(R.id.player_2);
        mPlayer3Container = findViewById(R.id.player_3);
        mPlayer4Container = findViewById(R.id.player_4);
        mPlayer5Container = findViewById(R.id.player_5);
        mPlayer6Container = findViewById(R.id.player_6);


        mPlayersContainer.setWeightSum(mListPlayers.size());

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        // PLAYER 1 //
        if(mListPlayers.size() > 0)
        {
            mFragmentPlayer1 = PlayerGameFragment.newInstance();
            PlayerGame playerGame = new PlayerGame(mListPlayers.get(0));
            mListPlayerGames.add(playerGame);
            mFragmentPlayer1.setPlayer(playerGame);
            ft.replace(R.id.player_1,mFragmentPlayer1);
        }
        else
        {
            ((ViewGroup) mPlayer1Container.getParent()).removeView(mPlayer1Container);
        }

        // PLAYER 2 //
        if(mListPlayers.size() > 1)
        {
            mFragmentPlayer2 = PlayerGameFragment.newInstance();
            PlayerGame playerGame = new PlayerGame(mListPlayers.get(1));
            mListPlayerGames.add(playerGame);
            mFragmentPlayer2.setPlayer(playerGame);
            ft.replace(R.id.player_2,mFragmentPlayer2);
        }
        else
        {
            ((ViewGroup) mPlayer2Container.getParent()).removeView(mPlayer2Container);
        }

        // PLAYER 3 //
        if(mListPlayers.size() > 2)
        {
            mFragmentPlayer3 = PlayerGameFragment.newInstance();
            PlayerGame playerGame = new PlayerGame(mListPlayers.get(2));
            mListPlayerGames.add(playerGame);
            mFragmentPlayer3.setPlayer(playerGame);
            ft.replace(R.id.player_3,mFragmentPlayer3);
        }
        else
        {
            ((ViewGroup) mPlayer3Container.getParent()).removeView(mPlayer3Container);
        }

        // PLAYER 4 //
        if(mListPlayers.size() > 3)
        {
            mFragmentPlayer4 = PlayerGameFragment.newInstance();
            PlayerGame playerGame = new PlayerGame(mListPlayers.get(3));
            mListPlayerGames.add(playerGame);
            mFragmentPlayer4.setPlayer(playerGame);
            ft.replace(R.id.player_4,mFragmentPlayer4);
        }
        else
        {
            ((ViewGroup) mPlayer4Container.getParent()).removeView(mPlayer4Container);
        }

        // PLAYER 5 //
        if(mListPlayers.size() > 4)
        {
            mFragmentPlayer5 = PlayerGameFragment.newInstance();
            PlayerGame playerGame = new PlayerGame(mListPlayers.get(4));
            mListPlayerGames.add(playerGame);
            mFragmentPlayer5.setPlayer(playerGame);
            ft.replace(R.id.player_5,mFragmentPlayer5);
        }
        else
        {
            ((ViewGroup) mPlayer5Container.getParent()).removeView(mPlayer5Container);
        }

        // PLAYER 6 //
        if(mListPlayers.size() > 5)
        {
            mFragmentPlayer6 = PlayerGameFragment.newInstance();
            PlayerGame playerGame = new PlayerGame(mListPlayers.get(5));
            mListPlayerGames.add(playerGame);
            mFragmentPlayer6.setPlayer(playerGame);
            ft.replace(R.id.player_6,mFragmentPlayer6);
        }
        else
        {
            ((ViewGroup) mPlayer6Container.getParent()).removeView(mPlayer6Container);
        }

        ft.commit();
    }

    @Override
    public void onBackPressed() {
        finish();
        //overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
