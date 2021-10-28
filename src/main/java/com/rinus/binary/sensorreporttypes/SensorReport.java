package com.rinus.binary.sensorreporttypes;
import com.google.gson.Gson;
import com.rinus.decoder.Payload;

public abstract class SensorReport {
    public String toJson()
    {
        return new Gson().toJson(this);
    }
    public abstract void parse(Payload payload);
}
