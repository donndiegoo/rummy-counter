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
public class PlayerGame implements MyJsonObject{

    private Player        mPlayer;
    private List<Integer> mPoints;

    public PlayerGame()
    {
        mPoints = new ArrayList<>();
    }

    public  PlayerGame(Player player)
    {
        mPlayer = player;
        mPoints = new ArrayList<>();
    }

    public Player getPlayer()
    {
        return mPlayer;
    }

    public List<Integer> getPoints()
    {
        return mPoints;
    }

    /**
     * Add new points to the list
     * @param value points to add
     * @return The total number of points
     */
    public int addPoints(int value)
    {
        mPoints.add(value);
        return getTotalPoints();
    }

    public int editPoints(int myPosition, int pointsInt) {
        mPoints.set(myPosition,pointsInt);

        return getTotalPoints();
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("player", MyApplication.mPlayers.getPlayers().indexOf(mPlayer));

        JSONArray jsonArray = new JSONArray();
        for (Integer point : mPoints) {
            JSONObject pointObject = new JSONObject();
            pointObject.put("point",point);
            jsonArray.put(pointObject);
        }
        jsonObj.put("points",jsonArray);
        return jsonObj;
    }

    @Override
    public void fromJSON(String json) throws JSONException{
        JSONObject jObj = new JSONObject(json);

        mPlayer = MyApplication.mPlayers.getPlayers().get(jObj.getInt("player"));

        JSONArray jArr = jObj.getJSONArray("points");
        for (int i=0; i < jArr.length(); i++) {
            JSONObject obj = jArr.getJSONObject(i);
            int point = obj.getInt("point");
            mPoints.add(point);
        }
    }

    public int getTotalPoints() {
        int total = 0;
        for(Integer point : mPoints)
        {
            total += point;
        }

        return total;
    }
}
