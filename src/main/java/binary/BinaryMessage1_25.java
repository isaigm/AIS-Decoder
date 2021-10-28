package binary;

import decoder.Payload;
import java.util.ArrayList;

public class BinaryMessage1_25 implements BinaryMessage {
    private int unit;
    private int amount;
    private final ArrayList<Cargo> cargos = new ArrayList<>();

    @Override
    public void parse(Payload payload) {
        unit = payload.getNextNbits(2).toInteger();
        amount = payload.getNextNbits(10).toInteger();
        while (payload.getRemainingSize() >= 17)
        {
            Cargo cargo = new Cargo();
            cargo.code = payload.getNextNbits(4).toInteger();
            cargo.subtype = payload.getNextNbits(13).toInteger();
            cargos.add(cargo);
        }

    }
}
