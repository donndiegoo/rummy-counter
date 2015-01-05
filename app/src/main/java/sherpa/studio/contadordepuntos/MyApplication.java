package sherpa.studio.contadordepuntos;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;

import sherpa.studio.contadordepuntos.Model.Avatar;
import sherpa.studio.contadordepuntos.Model.Game;
import sherpa.studio.contadordepuntos.Model.ListGames;
import sherpa.studio.contadordepuntos.Model.ListPlayers;

/**
 * Created by diego on 03/01/15.
 */
public class MyApplication extends Application{

    @SerializedName("players")
    public static ListPlayers mPlayers;
    @SerializedName("games")
    public static ListGames   mGames;

    public static Context mApplicationContext;



    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationContext = this;

        mPlayers = new ListPlayers();
        mGames = new ListGames();

        readGames();
        readPlayers();
    }

    public static void saveGames()
    {
        String filename = "games";
        FileOutputStream outputStream;

        try {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

            String gamesContent = gson.toJson(mGames.mListGames.get(0));
            outputStream = mApplicationContext.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(gamesContent.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readGames()
    {
//        String filename = "games";
//
//        try {
//            FileInputStream fis = mApplicationContext.openFileInput(filename);
//            InputStreamReader isr = new InputStreamReader(fis);
//            BufferedReader bufferedReader = new BufferedReader(isr);
//            StringBuilder sb = new StringBuilder();
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                sb.append(line);
//            }
//
//            String json = sb.toString();
//            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//            mGames = gson.fromJson(json,ListGames.class);
//
//            Log.d("MyApplication","test");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static void savePlayers()
    {
        String filename = "players";
        // save the object to file
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(mPlayers);

            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void readPlayers()
    {
        String filename = "players";
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            mPlayers = (ListPlayers) in.readObject();
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //FIXME correct this
        mPlayers.addPlayer("Pepe", Avatar.TYPE_FROG);
        mPlayers.addPlayer("Diego", Avatar.TYPE_CRAB);
        mPlayers.addPlayer("Manu", Avatar.TYPE_CHICKEN);
        mPlayers.addPlayer("Reyes", Avatar.TYPE_OCTOPUSS);
        mPlayers.addPlayer("Marta", Avatar.TYPE_CHICKEN);
        mPlayers.addPlayer("Antonio", Avatar.TYPE_SQUIRELL);
        mPlayers.addPlayer("Loli", Avatar.TYPE_CRAB);
        mPlayers.addPlayer("Jose", Avatar.TYPE_OCTOPUSS);
    }

    public static void addGame(Game game) {
        mGames.addGame(game);
        saveGames();
    }
}
