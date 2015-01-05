package sherpa.studio.contadordepuntos.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by diego on 03/01/15.
 */
public class Game {

    @SerializedName("date")
    private String mDate;
    @SerializedName("players")
    private List<PlayerGame> mPlayers;

    public Game(){}

    public Game(String date, List<PlayerGame> players)
    {
        mDate = date;
        mPlayers = players;
    }
}
