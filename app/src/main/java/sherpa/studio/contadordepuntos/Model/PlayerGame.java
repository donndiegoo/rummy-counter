package sherpa.studio.contadordepuntos.Model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 03/01/15.
 */
public class PlayerGame {

    @SerializedName("player")
    private Player        mPlayer;
    @SerializedName("points")
    private List<Integer> mPoints;

    public PlayerGame(){}

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
        int total = 0;
        for(Integer point : mPoints)
        {
            total += point;
        }

        return total;
    }

    public int editPoints(int myPosition, int pointsInt) {
        mPoints.set(myPosition,pointsInt);

        int total = 0;
        for(Integer point : mPoints)
        {
            total += point;
        }

        return total;
    }
}
