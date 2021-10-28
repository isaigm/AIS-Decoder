package binary;
import decoder.Payload;

public class BinaryMessage200_55 implements BinaryMessage {
    private int crew;
    private int passengers;
    private int personnel;
    @Override
    public void parse(Payload payload) {
        crew = payload.getNextNbits(8).toInteger();
        passengers = payload.getNextNbits(13).toInteger();
        personnel = payload.getNextNbits(8).toInteger();
    }
}
