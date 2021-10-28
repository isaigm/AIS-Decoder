package binary;
import decoder.Payload;
import java.util.ArrayList;

public class BinaryMessage1_17 implements BinaryMessage {
    private final ArrayList<Target> targets = new ArrayList<>();
    @Override
    public void parse(Payload payload) {
        while (payload.getRemainingSize() >= 120)
        {
            Target target = new Target();
            target.idtype = payload.getNextNbits(2).toInteger();
            target.id = payload.getNextNbits(42).toInteger();
            payload.getNextNbits(4); //sin usar
            target.lat = payload.getNextNbits(24).toSignedInt() * 0.0001f / 60;
            target.lon = payload.getNextNbits(25).toSignedInt() * 0.0001f / 60;
            target.course = payload.getNextNbits(9).toInteger();
            target.second = payload.getNextNbits(6).toInteger();
            target.speed = payload.getNextNbits(10).toInteger();
            targets.add(target);
        }
    }
}
