/**
 * Created by yashnak on 2/14/17.
 */
public class Game {

    /**+
     *
     * @param hasReplied
     * @param response
     * @return boolean value of whether a response has been given to bad input
     */

    public static boolean wrongInput(boolean hasReplied, String response) {
        if (!response.substring(0, 2).equalsIgnoreCase("go")) {
            TextIO.putln("I don't understand " + "\"" + response + "\"");
            hasReplied = true;
        }

        return hasReplied;
    }

    /**+
     *
     * @param response
     * @param currentRoom
     * @param room1
     * @return new room array where desired room is located
     */
    public static Rooms changeRoom(String response, Rooms currentRoom, Rooms[] room1) {
        boolean hasReplied = false;

        hasReplied = wrongInput(hasReplied, response);

        String concatResponse = "";
        concatResponse = response.substring(3);
        String directedRoom = "";


        //find the room that user requested after selecting direction
        for (int j = 0; j < currentRoom.getDirections().length; j++) {
            if (currentRoom.getDirections()[j].getDirection().equalsIgnoreCase(concatResponse)) {
                directedRoom = currentRoom.getDirections()[j].getRoom();
                hasReplied = true;
            }
        }

        if (!hasReplied) TextIO.putln("I can't go to " + concatResponse + "!");


        Rooms newRoom = room1[0];
        //change place in room array to that which has the specific room
        for (int k = 0; k < room1.length; k++) {
            if (directedRoom.equals(room1[k].getName())) {
                newRoom = room1[k];
            }
        }

        Boolean isValid = DirectionValidator.floorPlanIsValid(currentRoom, newRoom, room1);
        //System.out.println(isValid);

        return newRoom;

    }

    /**+
     *
     * @param room1
     * @param currentRoom
     * @param response
     */
    public static void gameStart(Rooms[] room1, Rooms currentRoom, String response) {

        while (!response.equals("quit") || !response.equals("exit") || !response.equals("done")) {

            TextIO.putln(currentRoom.getDescription());
            TextIO.put("From here, you can go: ");
            TextIO.put(currentRoom.getDirections()[0].getDirection());

            //list out options of directions user has
            for (int i = 1; i < currentRoom.getDirections().length; i++) {
                if (currentRoom.getDirections().length > 1) {
                    if (i == currentRoom.getDirections().length - 1) {
                        TextIO.put(", or " + currentRoom.getDirections()[i].getDirection());
                        break;
                    }
                    TextIO.put(", " + currentRoom.getDirections()[i].getDirection());
                }

            }

            response = TextIO.getlnString();
            if (response.equals(" ") || response.equals("")) {
                System.out.println("Type a valid response.");
                response = TextIO.getlnString();
            }
            if (response.equals("quit") || response.equals("exit") || response.equals("done")) {
                break;
            }

            //change room
            currentRoom = changeRoom(response, currentRoom, room1);




        }

    }

}

