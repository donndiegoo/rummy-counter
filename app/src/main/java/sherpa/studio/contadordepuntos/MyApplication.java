package sherpa.studio.contadordepuntos;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import sherpa.studio.contadordepuntos.Model.Avatar;
import sherpa.studio.contadordepuntos.Model.Game;
import sherpa.studio.contadordepuntos.Model.ListGames;
import sherpa.studio.contadordepuntos.Model.ListPlayers;

/**
 * Created by diego on 03/01/15.
 */
public class MyApplication extends Application{

    public static ListPlayers mPlayers;
    public static ListGames   mGames;

    public static Context mApplicationContext;



    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationContext = this;

        mPlayers = new ListPlayers();
        mGames = new ListGames();

        readPlayers();

        if(mPlayers.getPlayers().isEmpty())
        {
            mPlayers.addPlayer("Pepe", Avatar.TYPE_FROG);
            mPlayers.addPlayer("Diego", Avatar.TYPE_CRAB);
            mPlayers.addPlayer("Manu", Avatar.TYPE_CHICKEN);
            mPlayers.addPlayer("Reyes", Avatar.TYPE_OCTOPUSS);
            mPlayers.addPlayer("Marta", Avatar.TYPE_CHICKEN);
            mPlayers.addPlayer("Antonio", Avatar.TYPE_SQUIRELL);
            mPlayers.addPlayer("Loli", Avatar.TYPE_CRAB);
            mPlayers.addPlayer("Jose", Avatar.TYPE_OCTOPUSS);

            savePlayers();
        }


        readGames();



    }

    public static void saveGames()
    {
        String filename = "games";
        FileOutputStream outputStream;

        try {
            JSONObject jsonObject = mGames.toJSON();
            String gamesContent = jsonObject.toString();
            outputStream = mApplicationContext.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(gamesContent.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readGames()
    {
        String filename = "games";

        try {
            FileInputStream fis = mApplicationContext.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            String json = sb.toString();
            mGames = new ListGames();
            mGames.fromJSON(json);

            Log.d("MyApplication","test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void savePlayers()
    {
        String filename = "players";
        FileOutputStream outputStream;

        try {
            JSONObject jsonObject = mPlayers.toJSON();
            String playersContent = jsonObject.toString();
            outputStream = mApplicationContext.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(playersContent.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readPlayers()
    {
        String filename = "players";

        try {
            FileInputStream fis = mApplicationContext.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            String json = sb.toString();
            mPlayers = new ListPlayers();
            mPlayers.fromJSON(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addGame(Game game) {
        mGames.addGame(game);
        saveGames();
    }
}
