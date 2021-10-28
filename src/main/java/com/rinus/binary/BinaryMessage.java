package com.rinus.binary;
import com.rinus.decoder.Payload;
public interface BinaryMessage{
    void parse(Payload payload);
}
