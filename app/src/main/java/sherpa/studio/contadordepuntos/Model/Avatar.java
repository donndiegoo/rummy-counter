package sherpa.studio.contadordepuntos.Model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import sherpa.studio.contadordepuntos.MyApplication;
import sherpa.studio.contadordepuntos.R;

/**
 * Created by diego on 04/01/15.
 */
public class Avatar {
    public static final int TYPE_FROG = 0;
    public transient static final int TYPE_CRAB = 1;
    public transient static final int TYPE_CHICKEN = 2;
    public transient static final int TYPE_OCTOPUSS = 3;
    public transient static final int TYPE_SQUIRELL = 4;


    public transient Drawable mIcon;
    public transient int mColorMain;
    public transient int mColorSecondary;

    @SerializedName("type") private int mType;

    public Avatar()
    {
        mType = TYPE_CHICKEN;
    }

    public Avatar(int type)
    {
        mType = type;

        Context context = MyApplication.mApplicationContext;

        switch (type)
        {
            case TYPE_FROG:
            {
                mIcon = context.getResources().getDrawable(R.drawable.logo_frog);
                mColorMain = context.getResources().getColor(R.color.fbutton_color_emerald);
                mColorSecondary = context.getResources().getColor(R.color.fbutton_color_nephritis);
            }
            break;
            case TYPE_CRAB:
            {
                mIcon = context.getResources().getDrawable(R.drawable.logo_crab);
                mColorMain = context.getResources().getColor(R.color.fbutton_color_alizarin);
                mColorSecondary = context.getResources().getColor(R.color.fbutton_color_pomegranate);
            }
            break;
            case TYPE_CHICKEN:
            {
                mIcon = context.getResources().getDrawable(R.drawable.logo_chicken);
                mColorMain = context.getResources().getColor(R.color.fbutton_color_sun_flower);
                mColorSecondary = context.getResources().getColor(R.color.fbutton_color_orange);
            }
            break;
            case TYPE_OCTOPUSS:
            {
                mIcon = context.getResources().getDrawable(R.drawable.logo_octopuss);
                mColorMain = context.getResources().getColor(R.color.fbutton_color_amethyst);
                mColorSecondary = context.getResources().getColor(R.color.fbutton_color_wisteria);
            }
            break;
            case TYPE_SQUIRELL:
            {
                mIcon = context.getResources().getDrawable(R.drawable.logo_squirrel);
                mColorMain = context.getResources().getColor(R.color.fbutton_color_carrot);
                mColorSecondary = context.getResources().getColor(R.color.fbutton_color_pumpkin);
            }
            break;
        }
    }

}
