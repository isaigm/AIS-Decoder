package binary;
import decoder.Payload;


public class BinaryMessage1_20 implements BinaryMessage {
    private int linkage;
    private int berth_length;
    private float berth_depth;
    private int position;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int availability;
    private int agent;
    private int fuel;
    private int chandler;
    private int stevedore;
    private int electrical;
    private int water;
    private int customs;
    private int cartage;
    private int crane;
    private int lift;
    private int medical;
    private int navrepair;
    private int provisions;
    private int shiprepair;
    private int surveyor;
    private int steam;
    private int tugs;
    private int solidwaste;
    private int liquidwaste;
    private int hazardouswaste;
    private int ballast;
    private int additional;
    private int regional1;
    private int regional2;
    private int future1;
    private int future2;
    private String berth_name;
    private float berth_lon;
    private float berth_lat;

    @Override
    public void parse(Payload payload) {
        linkage = payload.getNextNbits(10).toInteger();
        berth_length = payload.getNextNbits(9).toInteger();
        berth_depth = payload.getNextNbits(8).toInteger();
        position = payload.getNextNbits(3).toInteger();
        month = payload.getNextNbits(4).toInteger();
        day = payload.getNextNbits(5).toInteger();
        hour = payload.getNextNbits(5).toInteger();
        minute = payload.getNextNbits(6).toInteger();
        availability = payload.getNextNbits(1).toInteger();
        agent = payload.getNextNbits(2).toInteger();
        fuel = payload.getNextNbits(2).toInteger();
        chandler = payload.getNextNbits(2).toInteger();
        stevedore = payload.getNextNbits(2).toInteger();
        electrical = payload.getNextNbits(2).toInteger();
        water = payload.getNextNbits(2).toInteger();
        customs = payload.getNextNbits(2).toInteger();
        cartage = payload.getNextNbits(2).toInteger();
        crane = payload.getNextNbits(2).toInteger();
        lift = payload.getNextNbits(2).toInteger();
        medical = payload.getNextNbits(2).toInteger();
        navrepair = payload.getNextNbits(2).toInteger();
        provisions = payload.getNextNbits(2).toInteger();
        shiprepair = payload.getNextNbits(2).toInteger();
        surveyor = payload.getNextNbits(2).toInteger();
        steam = payload.getNextNbits(2).toInteger();
        tugs = payload.getNextNbits(2).toInteger();
        solidwaste = payload.getNextNbits(2).toInteger();
        liquidwaste = payload.getNextNbits(2).toInteger();
        hazardouswaste = payload.getNextNbits(2).toInteger();
        ballast = payload.getNextNbits(2).toInteger();
        additional = payload.getNextNbits(2).toInteger();
        regional1 = payload.getNextNbits(2).toInteger();
        regional2 = payload.getNextNbits(2).toInteger();
        future1 = payload.getNextNbits(2).toInteger();
        future2 = payload.getNextNbits(2).toInteger();
        berth_name = payload.getNextNbits(120).toSixBitAscii();
        berth_lon = payload.getNextNbits(25).toSignedInt() * 0.0001f / 60;
        berth_lat = payload.getNextNbits(24).toSignedInt() * 0.0001f / 60;
    }
}
