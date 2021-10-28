package com.rinus.binary;
import com.rinus.decoder.Payload;

public class BinaryMessage1_15 implements BinaryMessage {
    private int airdraught;
    @Override
    public void parse(Payload payload) {
        airdraught = payload.getNextNbits(11).toInteger();
    }
}
