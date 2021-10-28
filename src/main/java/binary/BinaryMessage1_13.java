package binary;
import decoder.Payload;

public class BinaryMessage1_13 implements BinaryMessage {
    private String reason;
    private String closefrom;
    private String closeto;
    private int radius;
    private int extunit;
    private int fday;
    private int fmonth;
    private int fhour;
    private int fminute;
    private int tday;
    private int tmonth;
    private int thour;
    private int tminute;
    @Override
    public void parse(Payload payload) {
        reason = payload.getNextNbits(120).toSixBitAscii();
        closefrom = payload.getNextNbits(120).toSixBitAscii();
        closeto = payload.getNextNbits(120).toSixBitAscii();
        radius = payload.getNextNbits(10).toInteger();
        extunit = payload.getNextNbits(2).toInteger();
        fday = payload.getNextNbits(5).toInteger();
        fmonth = payload.getNextNbits(4).toInteger();
        fhour = payload.getNextNbits(5).toInteger();
        fminute = payload.getNextNbits(6).toInteger();
        tday = payload.getNextNbits(5).toInteger();
        tmonth = payload.getNextNbits(4).toInteger();
        thour = payload.getNextNbits(5).toInteger();
        tminute = payload.getNextNbits(6).toInteger();
    }
}
