package binary;

import binary.BinaryMessage;

import java.util.ArrayList;
public class BinaryMessage1_28 implements BinaryMessage {
    private int linkage;
    private int sender;
    private int rtype;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int duration;
    private int waycount;
    private final ArrayList<Waypoint> waypoints = new ArrayList<>();
    @Override
    public void parse(Payload payload) {
        linkage = payload.getNextNbits(10).toInteger();
        sender = payload.getNextNbits(3).toInteger();
        rtype = payload.getNextNbits(5).toInteger();
        month = payload.getNextNbits(4).toInteger();
        day = payload.getNextNbits(5).toInteger();
        hour = payload.getNextNbits(5).toInteger();
        minute = payload.getNextNbits(6).toInteger();
        duration = payload.getNextNbits(18).toInteger();
        waycount = payload.getNextNbits(5).toInteger();
        while (payload.getRemainingSize() >= 55)
        {
            Waypoint waypoint = new Waypoint();
            waypoint.longitude = payload.getNextNbits(28).toSignedInt() * 0.0001f / 60;
            waypoint.latitude = payload.getNextNbits(27).toSignedInt() * 0.0001f / 60;
            waypoints.add(waypoint);
        }
    }
}
