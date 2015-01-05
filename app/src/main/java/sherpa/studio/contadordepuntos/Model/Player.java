package sherpa.studio.contadordepuntos.Model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by diego on 03/01/15.
 */
public class Player {

    @SerializedName("name")
    private String mName;
    @SerializedName("avatar")
    private Avatar mAvatar;
    private boolean mSelected;

    public Player(){};

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
}
