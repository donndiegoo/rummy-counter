package sherpa.studio.contadordepuntos.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 03/01/15.
 */
public class ListPlayers{

    @SerializedName("listPlayers")
    private List<Player> mListPlayers;

    public ListPlayers()
    {
        mListPlayers = new ArrayList<>();
    }

    public void addPlayer(String name, int typeAvatar)
    {
        Player player = new Player(name, typeAvatar);
        mListPlayers.add(player);
    }


    public int getNewId()
    {
        return mListPlayers.size();
    }


    public List<Player> getPlayers() {
        return mListPlayers;
    }

    public List<Player> getSelectedPlayers() {
        List<Player> selectedPlayers = new ArrayList<>();
        for(Player player : mListPlayers)
        {
            if(player.isSelected())
            {
                selectedPlayers.add(player);
            }
        }

        return selectedPlayers;
    }
}
