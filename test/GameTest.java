import com.google.gson.Gson;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by yashnak on 2/14/17.
 */
public class GameTest {
    @org.junit.Before
    public void setUp() throws Exception {
        String result = Driver.readUrl(("https://courses.engr.illinois.edu/cs126/resources/siebel.json"));

        Gson gson = new Gson();
        Initial initial = gson.fromJson(result, Initial.class) ;
        Rooms[] room1 = initial.getRooms();

        TextIO.putln("Hello! Welcome to Zork.");
        String response = TextIO.getlnString();
        Rooms currentRoom = room1[0];
    }



    @org.junit.Test
    public void mainTest() throws Exception {
        String result = Driver.readUrl(("https://courses.engr.illinois.edu/cs126/resources/siebel.json"));

        Gson gson = new Gson();
        Initial initial = gson.fromJson(result, Initial.class) ;
        Rooms[] room1 = initial.getRooms();

        String response = TextIO.getlnString();
        Rooms currentRoom = room1[0];

        CheckInputOutput.setInputCaptureOutput("hi\ngo East\ngo North\ngo fsdkjfskd\n bananas\nexit");
        String expected = "Hello! Welcome to Zork.\nYou are on Matthews, outside the Siebel Center\nFrom here, you can go: East\nYou are in the west entry of Siebel Center.  You can see the elevator, the ACM office, and hallways to the north and east.\nYou are in the north hallway.  You can see Siebel 1112 and the door toward NCSA.\nI can't go to fsdkjfskd!\nYou are on Matthews, outside the Siebel Center\nFrom here, you can go: East\nI don't understand \"bananas\"\nYou are on Matthews, outside the Siebel Center\nFrom here, you can go: East\n";
        Game.gameStart(room1, currentRoom, response);
        int line = CheckInputOutput.checkCompleteOutput(expected);
        if(line>0) fail("Review incorrect output on line"+line);


    }

    public void emptyStringTest() throws Exception {
        String result = Driver.readUrl(("https://courses.engr.illinois.edu/cs126/resources/siebel.json"));

        Gson gson = new Gson();
        Initial initial = gson.fromJson(result, Initial.class) ;
        Rooms[] room1 = initial.getRooms();

        String response = TextIO.getlnString();
        Rooms currentRoom = room1[0];

        CheckInputOutput.setInputCaptureOutput("hi\n\ngo East\nexit");
        String expected = "Hello! Welcome to Zork.\nYou are on Matthews, outside the Siebel Center\nFrom here, you can go: East\nType a valid response.\nYou are in the west entry of Siebel Center.  You can see the elevator, the ACM office, and hallways to the north and east.\nFrom here, you can go: West, Northeast, North, or East\n";
        int line = CheckInputOutput.checkCompleteOutput(expected);
        if(line>0) fail("Review incorrect output on line"+line);


    }




}