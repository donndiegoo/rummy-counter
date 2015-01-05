package sherpa.studio.contadordepuntos.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.zip.Inflater;

import info.hoang8f.widget.FButton;
import sherpa.studio.contadordepuntos.Model.Player;
import sherpa.studio.contadordepuntos.Model.PlayerGame;
import sherpa.studio.contadordepuntos.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayerGameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerGameFragment extends Fragment {

    private View        mRootView;
    private ListView    mListView;
    private ViewGroup   mFooter;
    private FButton     mFooterPoints;

    private ImageView   mHeaderImage;
    private TextView    mHeaderName;
    private View        mHeaderDivider;

    private PlayerGame mPlayerGame;
    private ArrayAdapter<Integer> mAdapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PlayerGameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayerGameFragment newInstance() {
        PlayerGameFragment fragment = new PlayerGameFragment();
        return fragment;
    }

    public PlayerGameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_player_game, container, false);

        configureHeader();
        configureList();
        configureFooter();


        // Inflate the layout for this fragment
        return mRootView;
    }



    public void setPlayer(PlayerGame playerGame) {
        mPlayerGame = playerGame;
    }


    private void addPoints(int points)
    {
        int totalPoints = mPlayerGame.addPoints(points);
        mFooterPoints.setText(String.valueOf(totalPoints));
        mAdapter.notifyDataSetChanged();
    }

    private void editPoints(int myPosition, int pointsInt) {
        int totalPoints = mPlayerGame.editPoints(myPosition,pointsInt);
        mFooterPoints.setText(String.valueOf(totalPoints));
        mAdapter.notifyDataSetChanged();
    }

    private void configureFooter() {
        mFooter = (ViewGroup) mRootView.findViewById(R.id.points_footer);
        mFooterPoints = (FButton) mRootView.findViewById(R.id.points_footer_text);
        mFooterPoints.setText("0");

        mFooterPoints.setButtonColor(mPlayerGame.getPlayer().getAvatar().mColorMain);
        mFooterPoints.setShadowColor(mPlayerGame.getPlayer().getAvatar().mColorSecondary);

        mFooterPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View inputView = layoutInflater.inflate(R.layout.dialog_input,null);
                MaterialEditText input = (MaterialEditText) inputView.findViewById(R.id.edit_points);
                input.setPrimaryColor(mPlayerGame.getPlayer().getAvatar().mColorMain);

                new MaterialDialog.Builder(getActivity())
                        .icon(mPlayerGame.getPlayer().getAvatar().mIcon)
                        .title(mPlayerGame.getPlayer().getName())
                        .customView(inputView, true)
                        .positiveText("OK")
                        .positiveColor(mPlayerGame.getPlayer().getAvatar().mColorMain)
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                super.onPositive(dialog);
                                try{
                                    String points = ((EditText) dialog.getCustomView().findViewById(R.id.edit_points)).getText().toString();
                                    int pointsInt = Integer.parseInt(points);
                                    addPoints(pointsInt);
                                }
                                catch(Exception e)
                                {

                                }

                            }
                        })
                        .show();
            }
        });
    }

    private void configureList() {

        mListView = (ListView) mRootView.findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<Integer>(getActivity(),R.layout.item_points, R.id.textView,mPlayerGame.getPoints());
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View inputView = layoutInflater.inflate(R.layout.dialog_input,null);
                MaterialEditText input = (MaterialEditText) inputView.findViewById(R.id.edit_points);
                input.setText(String.valueOf(mPlayerGame.getPoints().get(position)));
                final int myPosition = position;

                (new MaterialDialog.Builder(getActivity())
                        .icon(mPlayerGame.getPlayer().getAvatar().mIcon)
                        .title(mPlayerGame.getPlayer().getName())
                        .customView(inputView, true)
                        .positiveText("OK")
                        .positiveColor(mPlayerGame.getPlayer().getAvatar().mColorMain)
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                super.onPositive(dialog);

                                try{
                                    String points = ((EditText) dialog.getCustomView().findViewById(R.id.edit_points)).getText().toString();
                                    int pointsInt = Integer.parseInt(points);
                                    editPoints(myPosition,pointsInt);
                                }
                                catch(Exception e)
                                {

                                }


                            }
                        })).show();
            }
        });

    }



    private void configureHeader() {
        mHeaderImage = (ImageView) mRootView.findViewById(R.id.header_logo);
        mHeaderName = (TextView) mRootView.findViewById(R.id.header_name);
        mHeaderDivider = (View) mRootView.findViewById(R.id.header_divider);

        mHeaderImage.setImageDrawable(mPlayerGame.getPlayer().getAvatar().mIcon);
        mHeaderDivider.setBackgroundColor(mPlayerGame.getPlayer().getAvatar().mColorMain);
        mHeaderName.setText(mPlayerGame.getPlayer().getName());
    }


}
