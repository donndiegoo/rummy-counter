package sherpa.studio.contadordepuntos.Model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import org.json.JSONException;
import org.json.JSONObject;

import sherpa.studio.contadordepuntos.MyApplication;
import sherpa.studio.contadordepuntos.R;

/**
 * Created by diego on 04/01/15.
 */
public class Avatar  implements MyJsonObject{
    public static final int TYPE_FROG = 0;
    public transient static final int TYPE_CRAB = 1;
    public transient static final int TYPE_CHICKEN = 2;
    public transient static final int TYPE_OCTOPUSS = 3;
    public transient static final int TYPE_SQUIRELL = 4;


    public transient Drawable mIcon;
    public transient int mColorMain;
    public transient int mColorSecondary;

    private int mType;
    private Context mContext;

    public Avatar()
    {
        mType = TYPE_CHICKEN;
    }

    public Avatar(int type)
    {
        mType = type;
        mContext = MyApplication.mApplicationContext;
        setUpAvatar();

    }

    private void setUpAvatar()
    {
        switch (mType)
        {
            case TYPE_FROG:
            {
                mIcon = mContext.getResources().getDrawable(R.drawable.logo_frog);
                mColorMain = mContext.getResources().getColor(R.color.fbutton_color_emerald);
                mColorSecondary = mContext.getResources().getColor(R.color.fbutton_color_nephritis);
            }
            break;
            case TYPE_CRAB:
            {
                mIcon = mContext.getResources().getDrawable(R.drawable.logo_crab);
                mColorMain = mContext.getResources().getColor(R.color.fbutton_color_alizarin);
                mColorSecondary = mContext.getResources().getColor(R.color.fbutton_color_pomegranate);
            }
            break;
            case TYPE_CHICKEN:
            {
                mIcon = mContext.getResources().getDrawable(R.drawable.logo_chicken);
                mColorMain = mContext.getResources().getColor(R.color.fbutton_color_sun_flower);
                mColorSecondary = mContext.getResources().getColor(R.color.fbutton_color_orange);
            }
            break;
            case TYPE_OCTOPUSS:
            {
                mIcon = mContext.getResources().getDrawable(R.drawable.logo_octopuss);
                mColorMain = mContext.getResources().getColor(R.color.fbutton_color_amethyst);
                mColorSecondary = mContext.getResources().getColor(R.color.fbutton_color_wisteria);
            }
            break;
            case TYPE_SQUIRELL:
            {
                mIcon = mContext.getResources().getDrawable(R.drawable.logo_squirrel);
                mColorMain = mContext.getResources().getColor(R.color.fbutton_color_carrot);
                mColorSecondary = mContext.getResources().getColor(R.color.fbutton_color_pumpkin);
            }
            break;
        }
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("type", mType);
        return jsonObj;
    }

    @Override
    public void fromJSON(String json) throws JSONException{
        JSONObject jObj = new JSONObject(json);
        mType = jObj.getInt("type");
        setUpAvatar();
    }

    public int getType() {
        return mType;
    }
}
