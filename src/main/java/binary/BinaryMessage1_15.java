package binary;
import decoder.Payload;

public class BinaryMessage1_15 implements BinaryMessage {
    private int airdraught;
    @Override
    public void parse(Payload payload) {
        airdraught = payload.getNextNbits(11).toInteger();
    }
}
