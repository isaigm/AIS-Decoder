public class Message7 extends Message {
    private int mmsi1;
    private int mmsiseq1;
    private int mmsi2;
    private int mmsiseq2;
    private int mmsi3;
    private int mmsiseq3;
    private int mmsi4;
    private int mmsiseq4;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        payload.getNextNbits(2); // sin usar
        if(payload.getData().size() - payload.getCurrentPos() >= 32)
        {
            mmsi1 = payload.getNextNbits(30).toInteger();
            mmsiseq1 = payload.getNextNbits(2).toInteger();
        }
        if(payload.getData().size() - payload.getCurrentPos() >= 32)
        {
            mmsi2 =  payload.getNextNbits(30).toInteger();
            mmsiseq2 = payload.getNextNbits(2).toInteger();
        }
        if(payload.getData().size() - payload.getCurrentPos() >= 32)
        {
            mmsi3 = payload.getNextNbits(30).toInteger();
            mmsiseq3 = payload.getNextNbits(2).toInteger();
        }
        if(payload.getData().size() - payload.getCurrentPos() >= 32)
        {
            mmsi4 = payload.getNextNbits(30).toInteger();
            mmsiseq4 = payload.getNextNbits(2).toInteger();
        }
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("MMSI1: %d\n", mmsi1);
        System.out.printf("MMSIseq1: %d\n", mmsiseq1);
        System.out.printf("MMSI2: %d\n", mmsi2);
        System.out.printf("MMSIseq2: %d\n", mmsiseq2);
        System.out.printf("MMSI3: %d\n", mmsi3);
        System.out.printf("MMSIseq3: %d\n", mmsiseq3);
        System.out.printf("MMSI4: %d\n", mmsi4);
        System.out.printf("MMSIseq4: %d\n", mmsiseq4);
    }
    public int getMmsi1() {
        return mmsi1;
    }
    public void setMmsi1(int mmsi1) {
        this.mmsi1 = mmsi1;
    }
    public int getMmsiseq1() {
        return mmsiseq1;
    }
    public void setMmsiseq1(int mmsiseq1) {
        this.mmsiseq1 = mmsiseq1;
    }
    public int getMmsi2() {
        return mmsi2;
    }
    public void setMmsi2(int mmsi2) {
        this.mmsi2 = mmsi2;
    }
    public int getMmsiseq2() {
        return mmsiseq2;
    }
    public void setMmsiseq2(int mmsiseq2) {
        this.mmsiseq2 = mmsiseq2;
    }
    public int getMmsi3() {
        return mmsi3;
    }
    public void setMmsi3(int mmsi3) {
        this.mmsi3 = mmsi3;
    }
    public int getMmsiseq3() {
        return mmsiseq3;
    }
    public void setMmsiseq3(int mmsiseq3) {
        this.mmsiseq3 = mmsiseq3;
    }
    public int getMmsi4() {
        return mmsi4;
    }
    public void setMmsi4(int mmsi4) {
        this.mmsi4 = mmsi4;
    }
    public int getMmsiseq4() {
        return mmsiseq4;
    }
    public void setMmsiseq4(int mmsiseq4) {
        this.mmsiseq4 = mmsiseq4;
    }
}
