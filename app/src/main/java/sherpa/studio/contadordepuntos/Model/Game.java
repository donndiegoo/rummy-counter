package sherpa.studio.contadordepuntos.Model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sherpa.studio.contadordepuntos.MyApplication;

/**
 * Created by diego on 03/01/15.
 */
public class Game implements MyJsonObject{

    private String mDate;
    private List<PlayerGame> mPlayers;

    public Game(){
        mPlayers = new ArrayList<>();
    }

    public Game(String date, List<PlayerGame> players)
    {
        mDate = date;
        mPlayers = players;
    }

    public List<PlayerGame> getPointsPlayers()
    {
        return mPlayers;
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("date", mDate);

        JSONArray jsonArray = new JSONArray();
        for (PlayerGame playerGame : mPlayers) {
            JSONObject pointObject = playerGame.toJSON();
            jsonArray.put(pointObject);
        }
        jsonObj.put("players",jsonArray);
        return jsonObj;
    }

    @Override
    public void fromJSON(String json) throws JSONException{
        JSONObject jObj = new JSONObject(json);

        mDate = jObj.getString("date");

        JSONArray jArr = jObj.getJSONArray("players");
        for (int i=0; i < jArr.length(); i++) {
            PlayerGame playerGame = new PlayerGame();
            playerGame.fromJSON(jArr.getJSONObject(i).toString());
            mPlayers.add(playerGame);
        }
    }

    public Player getWinner() {
        Player winner = mPlayers.get(0).getPlayer();
       int pointsWinner = 999999;
        for(PlayerGame pointsPlayer : getPointsPlayers())
        {
            int points = pointsPlayer.getTotalPoints();
            if(points < pointsWinner)
            {
                pointsWinner = points;
                winner = pointsPlayer.getPlayer();
            }
        }

        return winner;
    }

    public Player getLoser() {
        Player loser = mPlayers.get(0).getPlayer();
        int pointsLoser = -1;
        for(PlayerGame pointsPlayer : getPointsPlayers())
        {
            int points = pointsPlayer.getTotalPoints();
            if(points > pointsLoser)
            {
                pointsLoser = points;
                loser = pointsPlayer.getPlayer();
            }
        }

        return loser;
    }
}
