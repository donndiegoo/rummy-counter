package sherpa.studio.contadordepuntos.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 03/01/15.
 */
public class ListGames implements MyJsonObject {

    public List<Game> mListGames;

    public ListGames()
    {
        mListGames = new ArrayList<>();
    }


    public void addGame(Game game) {
        mListGames.add(game);
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObj = new JSONObject();

        JSONArray jsonArray = new JSONArray();
        for (Game game : mListGames) {
            JSONObject pointObject = game.toJSON();
            jsonArray.put(pointObject);
        }
        jsonObj.put("games",jsonArray);
        return jsonObj;
    }

    @Override
    public void fromJSON(String json) throws JSONException{
        JSONObject jObj = new JSONObject(json);

        JSONArray jArr = jObj.getJSONArray("games");
        for (int i=0; i < jArr.length(); i++) {
            Game game = new Game();
            JSONObject object = jArr.getJSONObject(i);
            game.fromJSON(object.toString());
            mListGames.add(game);
        }
    }
}
