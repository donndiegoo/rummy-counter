package sherpa.studio.contadordepuntos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import info.hoang8f.widget.FButton;
import sherpa.studio.contadordepuntos.Model.Player;
import sherpa.studio.contadordepuntos.R;

/**
 * Created by diego on 05/01/15.
 */
public class PlayersAdapter extends BaseAdapter {

    private Context mContext;
    private List<Player> mListPlayers;
    private LayoutInflater mInflater;

    public PlayersAdapter(Context c, List<Player> players) {
        mContext = c;
        mListPlayers = players;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mListPlayers.size();
    }

    @Override
    public Player getItem(int position) {
        return mListPlayers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final MyViewHolder holder;

        if(convertView == null)
        {
            convertView = mInflater.inflate(R.layout.item_player, null);
            holder = new MyViewHolder();
            holder.logo = (ImageView) convertView.findViewById(R.id.header_logo);
            holder.name = (TextView) convertView.findViewById(R.id.header_name);
            holder.button = (FButton) convertView.findViewById(R.id.button);
            convertView.setTag(holder);
        }
        else
        {
            holder = (MyViewHolder) convertView.getTag();
        }

        final Player player = getItem(position);
        holder.logo.setImageDrawable(player.getAvatar().mIcon);
        holder.name.setText(player.getName());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(player.isSelected())
               {
                   holder.button.setButtonColor(mContext.getResources().getColor(R.color.fbutton_color_clouds));
                   holder.button.setShadowColor(mContext.getResources().getColor(R.color.fbutton_color_silver));
                   holder.name.setTextColor(mContext.getResources().getColor(R.color.material_blue_grey_800));
               }
               else
               {
                   holder.button.setButtonColor(player.getAvatar().mColorMain);
                   holder.button.setShadowColor(player.getAvatar().mColorSecondary);
                   holder.name.setTextColor(mContext.getResources().getColor(android.R.color.white));
               }

               player.setSelected(!player.isSelected());
            }
        });


        return convertView;
    }

    private class MyViewHolder
    {
        public ImageView logo;
        public TextView  name;
        public FButton   button;
    }
}
