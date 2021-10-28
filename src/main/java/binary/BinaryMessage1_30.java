package binary;
import decoder.Payload;
public class BinaryMessage1_30 implements BinaryMessage {
    private int linkage;
    private String description;
    @Override
    public void parse(Payload payload) {
        linkage = payload.getNextNbits(10).toInteger();
        description = payload.getLastBits().toSixBitAscii();
    }
}
