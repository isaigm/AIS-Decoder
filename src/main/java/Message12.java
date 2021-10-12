public class Message12 extends Message{
    private int seqno;
    private int dest_mmsi;
    private int retransmit;
    private String text;

    @Override
    public void parse(Payload payload) throws Exception
    {
        super.parse(payload);
        seqno = payload.getData().getNbits(2).toInteger();
        dest_mmsi = payload.getData().getNbits(30).toInteger();
        retransmit = payload.getData().getNbits(1).toInteger();
        payload.getData().getNbits(1);
        text = payload.getData().toSixBitAscii();
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("Número de secuencia: %d\n", seqno);
        System.out.printf("MMSI destino: %d\n", dest_mmsi);
        System.out.printf("Flag de transmisión: %d\n", retransmit);
    }
    public int getDest_mmsi() {
        return dest_mmsi;
    }

    public void setDest_mmsi(int dest_mmsi) {
        this.dest_mmsi = dest_mmsi;
    }
    public int getSeqno() {
        return seqno;
    }

    public void setSeqno(int seqno) {
        this.seqno = seqno;
    }

    public int getRetransmit() {
        return retransmit;
    }

    public void setRetransmit(int retransmit) {
        this.retransmit = retransmit;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
