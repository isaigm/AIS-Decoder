public class Message16 extends Message {
    private int mmsi1;
    private int offset1;
    private int increment1;
    private int mmsi2;
    private int offset2;
    private int increment2;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        if(payload.size() != 96 || payload.size() != 144) throw new NMEAMessageException("Mensaje tipo 16: longitud erronea");
        boolean twoStation = payload.size() == 144;
        super.parse(payload);
        payload.getNextNbits(2); //sin usar
        mmsi1 = payload.getNextNbits(30).toInteger();
        offset1 = payload.getNextNbits(12).toInteger();
        increment1 = payload.getNextNbits(10).toInteger();
        if(twoStation)
        {
            mmsi2 = payload.getNextNbits(30).toInteger();
            offset2 = payload.getNextNbits(12).toInteger();
            increment2 = payload.getNextNbits(10).toInteger();
        }
    }
    public int getMmsi1() {
        return mmsi1;
    }
    public void setMmsi1(int mmsi1) {
        this.mmsi1 = mmsi1;
    }
    public int getOffset1() {
        return offset1;
    }
    public void setOffset1(int offset1) {
        this.offset1 = offset1;
    }
    public int getIncrement1() {
        return increment1;
    }
    public void setIncrement1(int increment1) {
        this.increment1 = increment1;
    }
    public int getMmsi2() {
        return mmsi2;
    }
    public void setMmsi2(int mmsi2) {
        this.mmsi2 = mmsi2;
    }
    public int getOffset2() {
        return offset2;
    }
    public void setOffset2(int offset2) {
        this.offset2 = offset2;
    }
    public int getIncrement2() {
        return increment2;
    }
    public void setIncrement2(int increment2) {
        this.increment2 = increment2;
    }
}
