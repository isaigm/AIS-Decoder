public class Message6 extends Message {
    private int seqno; //numero de secuencia
    private int dest_mmsi;
    private int retransmit;
    private int dac; //codigo de area designada
    private int fid;
    private BinaryString data;
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
    public void parse(Payload payload) throws Exception
    {
        super.parse(payload);
        seqno = payload.getData().getNbits(2).toInteger();
        dest_mmsi = payload.getData().getNbits(30).toInteger();
        retransmit = payload.getData().getNbits(1).toInteger();
        payload.getData().getNbits(1); //sin usar
        dac = payload.getData().getNbits(10).toInteger();
        fid = payload.getData().getNbits(6).toInteger();
        data = payload.getData().getNbits(payload.size());
        if(dac == 1 && fid == 12)
        {
            lastport = data.getNbits(30).toSixBitAscii();
            lmonth = data.getNbits(4).toInteger();
            lday = data.getNbits(5).toInteger();
            lhour = data.getNbits(5).toInteger();
            lminute = data.getNbits(6).toInteger();
            nextport = data.getNbits(30).toSixBitAscii();
            nmonth = data.getNbits(4).toInteger();
            nday = data.getNbits(5).toInteger();
            nhour = data.getNbits(5).toInteger();
            nminute = data.getNbits(6).toInteger();
            dangerous = data.getNbits(120).toSixBitAscii();
            imdcat = data.getNbits(24).toSixBitAscii();
            unid = data.getNbits(13).toInteger();
            amount = data.getNbits(10).toInteger();
            unit = data.getNbits(2).toInteger();
        }else if(dac == 1 && fid == 14)
        {
            month = data.getNbits(4).toInteger();
            day = data.getNbits(5).toInteger();
            lat = data.getNbits(27).toSignedInt() * 0.0001f / 60;;
            lon = data.getNbits(28).toSignedInt() * 0.0001f / 60;;
            from_hour = data.getNbits(5).toInteger();
            from_min = data.getNbits(6).toInteger();
            to_hour = data.getNbits(5).toInteger();
            to_min = data.getNbits(6).toInteger();
            cdir = data.getNbits(9).toInteger();
            cspeed = data.getNbits(7).toInteger() * 0.1f;
        }
        else if(dac == 1 && fid == 16)
        {
            persons = data.getNbits(13).toInteger();
        }else if(dac == 1 && fid == 18)
        {
            linkage = data.getNbits(10).toInteger();
            month = data.getNbits(4).toInteger();
            day = data.getNbits(5).toInteger();
            hour = data.getNbits(5).toInteger();
            minute = data.getNbits(6).toInteger();
            portname = data.getNbits(120).toSixBitAscii();
            destination = data.getNbits(30).toSixBitAscii();
            lon = data.getNbits(25).toSignedInt() * 0.0001f / 60;;
            lon = data.getNbits(26).toSignedInt() * 0.0001f / 60;;
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
    public BinaryString getData() {
        return data;
    }
    public void setData(BinaryString data) {
        this.data = data;
    }
}
