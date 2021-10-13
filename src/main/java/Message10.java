public class Message10 extends Message{
    private int dest_mmsi;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        payload.getData().getNbits(2);//sin usar
        dest_mmsi =  payload.getData().getNbits(30).toInteger();
        payload.getData().getNbits(2);//sin usar
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("MMSI destino: %d\n", dest_mmsi);
    }
    public int getDest_mmsi() {
        return dest_mmsi;
    }

    public void setDest_mmsi(int dest_mmsi) {
        this.dest_mmsi = dest_mmsi;
    }
}
