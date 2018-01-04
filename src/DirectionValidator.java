/**
 * Created by yashnak on 2/14/17.
 */
public class DirectionValidator {

    /**+
     *
     * @param currentRoom
     * @param desiredRoom
     * @param room1
     * @return boolean value of whether route from A->B will give you a path from B->A
     */

    public static boolean floorPlanIsValid(Rooms currentRoom, Rooms desiredRoom, Rooms[] room1) {

        for (int i = 0; i < room1.length; i++) {
            //if the current room exists in the desired room's paths, then it is possible to get back as well
            if (currentRoom.getName().equals(desiredRoom.getDirections()[i].getRoom())){
                return true;
            }
            else {return false;}
        }

        return false;


    }




}
