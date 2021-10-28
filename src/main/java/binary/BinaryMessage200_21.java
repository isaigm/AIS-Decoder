package binary;
import decoder.Payload;

public class BinaryMessage200_21 implements BinaryMessage {
    private String country;
    private String locode;
    private String section;
    private String terminal;
    private String hectometre;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int tugs;
    private int airdraught;
    @Override
    public void parse(Payload payload) {
        country = payload.getNextNbits(12).toSixBitAscii();
        locode = payload.getNextNbits(18).toSixBitAscii();
        section = payload.getNextNbits(30).toSixBitAscii();
        terminal = payload.getNextNbits(30).toSixBitAscii();
        hectometre = payload.getNextNbits(30).toSixBitAscii();
        month = payload.getNextNbits(4).toInteger();
        day = payload.getNextNbits(5).toInteger();
        hour = payload.getNextNbits(5).toInteger();
        minute = payload.getNextNbits(6).toInteger();
        tugs = payload.getNextNbits(3).toInteger();
        airdraught = payload.getNextNbits(12).toInteger();
    }
}
