package sherpa.studio.contadordepuntos.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 03/01/15.
 */
public class ListGames {

    @SerializedName("test")
    private String test;

    @SerializedName("listGames")
    public List<Game> mListGames;

    public ListGames()
    {
        test = "testContent";
        mListGames = new ArrayList<>();
    }


    public void addGame(Game game) {
        mListGames.add(game);
    }
}
