import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.lang.reflect.Type;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


import com.google.gson.Gson;

/**
 * Created by yashnak on 2/13/17.
 */
public class Driver {

    public static String readUrl(String urlString) throws Exception{
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            if (reader != null)
                reader.close();
        }

    }


    public static void main(String[] args) throws Exception {

        String result = readUrl(RoomKey.getUrl());

        Gson gson = new Gson();
        Initial initial = gson.fromJson(result, Initial.class) ;
        Rooms[] room1 = initial.getRooms();

        TextIO.putln("Hello! Welcome to Zork.");
        String response = TextIO.getlnString();
        Rooms currentRoom = room1[0];
        Game.gameStart(room1, currentRoom, response);



    }


}
