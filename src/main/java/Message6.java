public class Message6 extends Message {
    private int seqno; //numero de secuencia
    private int dest_mmsi;
    private int retransmit;
    private int dac; //codigo de area designada
    private int fid;
    private BitString data;
    //DAC = 1 FID = 12
    private String lastport;
    private int lmonth;
    private int lday;
    private int lhour;
    private int lminute;
    private String nextport;
    private int nmonth;
    private int nday;
    private int nhour;
    private int nminute;
    private String dangerous;
    private String imdcat;
    private int unid;
    private int amount;
    private int unit;
    //DAC = 1 FID = 14
    private float lat;
    private float lon;
    private int from_hour;
    private int from_min;
    private int to_hour;
    private int to_min;
    private int cdir;
    private float cspeed;

    //DAC = 1 FID = 16
    private int persons;
    //DAC = 1 FID = 18
    private int linkage;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private String portname;
    private String destination;
    private float longitude;
    private float latitude;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        seqno = payload.getNextNbits(2).toInteger();
        dest_mmsi = payload.getNextNbits(30).toInteger();
        retransmit = payload.getNextNbits(1).toInteger();
        payload.getNextNbits(1); //sin usar
        dac = payload.getNextNbits(10).toInteger();
        fid = payload.getNextNbits(6).toInteger();
        data = payload.getNextNbits(payload.size() - payload.getCurrentPos());
        if(dac == 1 && fid == 12)
        {
            lastport = payload.getNextNbits(30).toSixBitAscii();
            lmonth = payload.getNextNbits(4).toInteger();
            lday = payload.getNextNbits(5).toInteger();
            lhour = payload.getNextNbits(5).toInteger();
            lminute = payload.getNextNbits(6).toInteger();
            nextport = payload.getNextNbits(30).toSixBitAscii();
            nmonth = payload.getNextNbits(4).toInteger();
            nday = payload.getNextNbits(5).toInteger();
            nhour = payload.getNextNbits(5).toInteger();
            nminute = payload.getNextNbits(6).toInteger();
            dangerous = payload.getNextNbits(120).toSixBitAscii();
            imdcat = payload.getNextNbits(24).toSixBitAscii();
            unid = payload.getNextNbits(13).toInteger();
            amount = payload.getNextNbits(10).toInteger();
            unit = payload.getNextNbits(2).toInteger();
        }else if(dac == 1 && fid == 14)
        {
            month = payload.getNextNbits(4).toInteger();
            day = payload.getNextNbits(5).toInteger();
            lat = payload.getNextNbits(27).toSignedInt() * 0.0001f / 60;;
            lon = payload.getNextNbits(28).toSignedInt() * 0.0001f / 60;;
            from_hour = payload.getNextNbits(5).toInteger();
            from_min = payload.getNextNbits(6).toInteger();
            to_hour = payload.getNextNbits(5).toInteger();
            to_min = payload.getNextNbits(6).toInteger();
            cdir = payload.getNextNbits(9).toInteger();
            cspeed = payload.getNextNbits(7).toInteger() * 0.1f;
        }
        else if(dac == 1 && fid == 16)
        {
            persons = payload.getNextNbits(13).toInteger();
        }else if(dac == 1 && fid == 18)
        {
            linkage = payload.getNextNbits(10).toInteger();
            month = payload.getNextNbits(4).toInteger();
            day = payload.getNextNbits(5).toInteger();
            hour = payload.getNextNbits(5).toInteger();
            minute = payload.getNextNbits(6).toInteger();
            portname = payload.getNextNbits(120).toSixBitAscii();
            destination = payload.getNextNbits(30).toSixBitAscii();
            lon = payload.getNextNbits(25).toSignedInt() * 0.0001f / 60;;
            lon = payload.getNextNbits(26).toSignedInt() * 0.0001f / 60;;
        }
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("Número de secuencia: %d\n", seqno);
        System.out.printf("MMSI destino: %d\n", dest_mmsi);
        System.out.printf("Flag de transmisión: %d\n", retransmit);
        System.out.printf("FID: %d\n", fid);
        System.out.printf("Código de área de asignada: %d\n", dac);
    }
    public int getSeqno() {
        return seqno;
    }
    public void setSeqno(int seqno) {
        this.seqno = seqno;
    }
    public int getDest_mmsi() {
        return dest_mmsi;
    }
    public void setDest_mmsi(int dest_mmsi) {
        this.dest_mmsi = dest_mmsi;
    }
    public int getRetransmit() {
        return retransmit;
    }
    public void setRetransmit(int retransmit) {
        this.retransmit = retransmit;
    }
    public int getDac() {
        return dac;
    }
    public void setDac(int dac) {
        this.dac = dac;
    }
    public int getFid() {
        return fid;
    }
    public void setFid(int fid) {
        this.fid = fid;
    }
    public BitString getData() {
        return data;
    }
    public void setData(BitString data) {
        this.data = data;
    }
}
