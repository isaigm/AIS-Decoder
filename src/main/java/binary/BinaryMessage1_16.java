package binary;
import decoder.Payload;

public class BinaryMessage1_16 implements BinaryMessage {
    private int persons;
    @Override
    public void parse(Payload payload) {
        if(payload.size() == 72) {
            persons = payload.getNextNbits(14).toInteger();
        }else if(payload.size() == 136)
        {
            persons = payload.getNextNbits(13).toInteger();
        }
    }
}
