package binary;
import decoder.Payload;
public class BinaryMessage1_12 implements BinaryMessage {
    private String lastport;
    private int lmonth;
    private int lday;
    private int lhour;
    private int lminute;
    private String nextport;
    private int nmonth;
    private int nday;
    private int nhour;
    private int nminute;
    private String dangerous;
    private String imdcat;
    private int unid;
    private int amount;
    private int unit;


    @Override
    public void parse(Payload payload) {
        lastport = payload.getNextNbits(30).toSixBitAscii();
        lmonth = payload.getNextNbits(4).toInteger();
        lday = payload.getNextNbits(5).toInteger();
        lhour = payload.getNextNbits(5).toInteger();
        lminute = payload.getNextNbits(6).toInteger();
        nextport = payload.getNextNbits(30).toSixBitAscii();
        nmonth = payload.getNextNbits(4).toInteger();
        nday = payload.getNextNbits(5).toInteger();
        nhour = payload.getNextNbits(5).toInteger();
        nminute = payload.getNextNbits(6).toInteger();
        dangerous = payload.getNextNbits(120).toSixBitAscii();
        imdcat = payload.getNextNbits(24).toSixBitAscii();
        unid = payload.getNextNbits(13).toInteger();
        amount = payload.getNextNbits(10).toInteger();
        unit = payload.getNextNbits(2).toInteger();
    }
}
