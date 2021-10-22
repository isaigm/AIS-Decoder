public class Message15 extends Message {
    private int mmsi1;
    private int type1_1;
    private int offset1_1;
    private int type1_2;
    private int offset1_2;
    private int mmsi2;
    private int type2_1;
    private int offset2_1;
    @Override
    public void parse(Payload payload) throws NMEAMessageException {
        if(payload.size() != 88 || payload.size() != 160 || payload.size() != 110 || payload.size() != 112)
        {
            throw new NMEAMessageException("Mensaje tipo 15: longitud erronea");
        }
        super.parse(payload);
        payload.getNextNbits(2); //sin uso
        mmsi1 = payload.getNextNbits(30).toInteger();
        type1_1 = payload.getNextNbits(6).toInteger();
        offset1_1 = payload.getNextNbits(12).toInteger();
        payload.getNextNbits(2); //sin uso
        type1_2 = payload.getNextNbits(6).toInteger();
        offset1_2 = payload.getNextNbits(12).toInteger();
        payload.getNextNbits(2); //sin usar
        mmsi2 = payload.getNextNbits(30).toInteger();
        type2_1 = payload.getNextNbits(6).toInteger();
        offset2_1 = payload.getNextNbits(12).toInteger();
    }
}
