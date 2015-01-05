package sherpa.studio.contadordepuntos.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import info.hoang8f.widget.FButton;
import sherpa.studio.contadordepuntos.Model.Game;
import sherpa.studio.contadordepuntos.Model.Player;
import sherpa.studio.contadordepuntos.Model.PlayerGame;
import sherpa.studio.contadordepuntos.MyApplication;
import sherpa.studio.contadordepuntos.R;
import sherpa.studio.contadordepuntos.adapter.PlayersAdapter;


public class StatisticsActivity extends ActionBarActivity {

    private BarChart mPointsPerUser;
    private PieChart mWinningGames;

    private TextView mTotalGames;
    private TextView mTotalPoints;
    private TextView mMaxPointsGame;
    private TextView mMaxPointsRound;


    public static void startActivity(Context context)
    {
        Intent intent = new Intent(context, StatisticsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        mTotalGames = (TextView) findViewById(R.id.statistics_total_games);
        mTotalPoints = (TextView) findViewById(R.id.statistics_total_points);
        mMaxPointsGame = (TextView) findViewById(R.id.statistics_max_points_game);
        mMaxPointsRound = (TextView) findViewById(R.id.statistics_max_points_round);

//        mPointsPerUser = (BarChart) findViewById(R.id.statistics_points);
//        mPointsPerUser.setData(generateBarData(1, 20000, 12));

        mWinningGames = (PieChart) findViewById(R.id.statistics_winning_games);

        createStatistics();


    }

    private void createStatistics() {

        List<Game>      listGames       = MyApplication.mGames.mListGames;

        int numberGames                 = listGames.size();
        int numPoints                   = 0;

        Map<Player,Integer> pointsPlayer             = new ArrayMap<>();
        Map<Player,Integer> wonGamesPlayer           = new ArrayMap<>();
        Map<Player,Integer> lostGamesPlayer          = new ArrayMap<>();
        Map<Player,Integer> recordPointsGamePlayer   = new ArrayMap<>();
        Map<Player,Integer> recordPointsRoundPlayer  = new ArrayMap<>();

        Map.Entry<Player,Integer> recordPointsGame = null;
        Map.Entry<Player,Integer> recordPointsRound = null;

        for(Game game : listGames)
        {
            // Winner games
            Player winner = game.getWinner();

            int winningGamesPlayer = wonGamesPlayer.containsKey(winner) ? wonGamesPlayer.get(winner) : 0;
            winningGamesPlayer++;
            wonGamesPlayer.put(winner,winningGamesPlayer);

            // Loser games
            Player loser = game.getLoser();
            int losingGamesPlayer = lostGamesPlayer.containsKey(loser) ? lostGamesPlayer.get(loser) : 0;
            losingGamesPlayer++;
            lostGamesPlayer.put(loser,losingGamesPlayer);


            List<PlayerGame> pointsPlayerList = game.getPointsPlayers();
            for(PlayerGame playerGame : pointsPlayerList)
            {
                Player player = playerGame.getPlayer();
                int totalPoints = playerGame.getTotalPoints();

                // total points
                numPoints += totalPoints;

                // total points player
                int totalPointsPlayer = pointsPlayer.containsKey(player) ? pointsPlayer.get(player) : 0;
                totalPointsPlayer += totalPoints;
                pointsPlayer.put(player,totalPointsPlayer);

                // record points per game
                int maxPointsGame = recordPointsGamePlayer.containsKey(player) ? recordPointsGamePlayer.get(player) : 0;
                if(totalPoints > maxPointsGame)
                {
                    recordPointsGamePlayer.put(player,totalPoints);
                }

                // record points per round
                for(int points : playerGame.getPoints())
                {
                    int maxPointsRound = recordPointsRoundPlayer.containsKey(player) ? recordPointsRoundPlayer.get(player) : 0;
                    if(points > maxPointsRound)
                    {
                        recordPointsRoundPlayer.put(player,points);
                    }
                }
            }

        }


        int maxPointsGame = -1;
        for(Map.Entry<Player,Integer> entry : recordPointsGamePlayer.entrySet())
        {
            if(entry.getValue() > maxPointsGame)
            {
                maxPointsGame = entry.getValue();
                recordPointsGame = entry;
            }
        }

        int maxPointsRound = -1;
        for(Map.Entry<Player,Integer> entry : recordPointsRoundPlayer.entrySet())
        {
            if(entry.getValue() > maxPointsRound)
            {
                maxPointsRound = entry.getValue();
                recordPointsRound = entry;
            }
        }


        mTotalGames.setText(Html.fromHtml("N° TOTAL DE PARTIDAS: <b>" + numberGames + "</b>"));
        mTotalPoints.setText(Html.fromHtml("N° TOTAL DE PUNTOS: <b>" + numPoints + "</b>"));

        if(recordPointsGame != null)  mMaxPointsGame.setText(Html.fromHtml("RECORD DE PUNTOS EN UNA PARTIDA:\n<b>" + recordPointsGame.getValue() + " (" + recordPointsGame.getKey().getName() + ")</b>"));
        else mMaxPointsGame.setVisibility(View.GONE);

        if(recordPointsRound != null)  mMaxPointsRound.setText(Html.fromHtml("RECORD DE PUNTOS EN UNA JUGADA:\n<b>" + recordPointsRound.getValue() + " (" + recordPointsRound.getKey().getName() + ")</b>"));
        else mMaxPointsRound.setVisibility(View.GONE);


        mWinningGames.setData(generatePieData(wonGamesPlayer, numberGames));

    }

    protected BarData generateBarData(int dataSets, float range, int count) {

        ArrayList<BarDataSet> sets = new ArrayList<BarDataSet>();

        for(int i = 0; i < dataSets; i++) {

            ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

//            entries = FileUtils.loadEntriesFromAssets(getActivity().getAssets(), "stacked_bars.txt");

            for(int j = 0; j < count; j++) {
                entries.add(new BarEntry((float) (Math.random() * range) + range / 4, j));
            }

            BarDataSet ds = new BarDataSet(entries, getLabel(i));
            ds.setColors(ColorTemplate.JOYFUL_COLORS);
            sets.add(ds);
        }

        BarData d = new BarData(ChartData.generateXVals(0, count), sets);
        return d;
    }

    private String[] mLabels = new String[] { "Company A", "Company B", "Company C", "Company D", "Company E", "Company F" };
//    private String[] mXVals = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec" };

    private String getLabel(int i) {
        return mLabels[i];
    }

    protected PieData generatePieData(Map<Player, Integer> wonGamesPlayer, int numberGames) {

        ArrayList<Entry> entries1 = new ArrayList<Entry>();
        ArrayList<String> xVals = new ArrayList<String>();

        int i = 0;
        for(Map.Entry<Player,Integer> entry : wonGamesPlayer.entrySet())
        {
            xVals.add(entry.getKey().getName());
            entries1.add(new Entry((float) (entry.getValue() * 100) / numberGames, i++));
        }

        PieDataSet ds1 = new PieDataSet(entries1,"");
        ds1.setColors(ColorTemplate.COLORFUL_COLORS);
        ds1.setSliceSpace(2f);

        PieData d = new PieData(xVals, ds1);
        return d;
    }
}
