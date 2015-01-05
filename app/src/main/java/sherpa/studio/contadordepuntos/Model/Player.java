package sherpa.studio.contadordepuntos.Model;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by diego on 03/01/15.
 */
public class Player implements MyJsonObject {

    private String mName;
    private Avatar mAvatar;
    private boolean mSelected;

    public Player(){}

    /**
     * Constructor of the class
     * @param name Name of the player
     * @param typeAvatar Avatar(0: Frog, 1: Crab)
     */
    public Player(String name, int typeAvatar)
    {
        mName = name;
        mAvatar =  new Avatar(typeAvatar);
    }

    public String getName() {
        return mName;
    }

    public Avatar getAvatar() {
        return mAvatar;
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void setSelected(boolean selected) {
        this.mSelected = selected;
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name", mName);
        jsonObj.put("avatar", mAvatar.getType());
        return jsonObj;
    }

    @Override
    public void fromJSON(String json) throws JSONException{
        JSONObject jObj = new JSONObject(json);
        mName = jObj.getString("name");
        mAvatar = new Avatar(jObj.getInt("avatar"));
    }
}
