package sherpa.studio.contadordepuntos.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 03/01/15.
 */
public class ListPlayers implements MyJsonObject{

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

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObj = new JSONObject();

        JSONArray jsonArray = new JSONArray();
        for (Player player : mListPlayers) {
            JSONObject pointObject = player.toJSON();
            jsonArray.put(pointObject);
        }
        jsonObj.put("players",jsonArray);
        return jsonObj;
    }

    @Override
    public void fromJSON(String json) throws JSONException{
        JSONObject jObj = new JSONObject(json);

        JSONArray jArr = jObj.getJSONArray("players");
        for (int i=0; i < jArr.length(); i++) {
            Player player = new Player();
            player.fromJSON(jArr.getString(i));
            mListPlayers.add(player);
        }
    }
}
