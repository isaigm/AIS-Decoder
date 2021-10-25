public class Message25 extends Message{
    private int addresed;
    private int structured;
    private int dest_mmsi;
    private int app_id;
    private BitString data;

    @Override
    public void parse(Payload payload) throws NMEAMessageException {
        super.parse(payload);
        if(payload.size() >= 168)
        {
            throw new NMEAMessageException("Mensaje tipo 25: longitud erronea");
        }
        addresed = payload.getNextNbits(1).toInteger();
        structured = payload.getNextNbits(1).toInteger();
        if(addresed == 1)
        {
            dest_mmsi = payload.getNextNbits(30).toInteger();
        }
        if(structured == 1)
        {
            app_id = payload.getNextNbits(16).toInteger();
        }

        data = payload.getLastBits();
    }

}
