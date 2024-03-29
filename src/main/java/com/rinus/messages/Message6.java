/*
    +-------+-----+----------------------+------------+---+-----------------------------------------------+
    | Field | Len | Description          | Member     | T | Units                                         |
    +-------+-----+----------------------+------------+---+-----------------------------------------------+
    | 0-5   | 6   | Message Type         | type       | u | Constant: 6                                   |
    +-------+-----+----------------------+------------+---+-----------------------------------------------+
    | 6-7   | 2   | Repeat Indicator     | repeat     | u | As in Common Navigation Block                 |
    +-------+-----+----------------------+------------+---+-----------------------------------------------+
    | 8-37  | 30  | Source MMSI          | mmsi       | u | 9 decimal digits                              |
    +-------+-----+----------------------+------------+---+-----------------------------------------------+
    | 38-39 | 2   | Sequence Number      | seqno      | u | Unsigned integer 0-3                          |
    +-------+-----+----------------------+------------+---+-----------------------------------------------+
    | 40-69 | 30  | Destination MMSI     | dest_mmsi  | u | 9 decimal digits                              |
    +-------+-----+----------------------+------------+---+-----------------------------------------------+
    | 70    | 1   | Retransmit flag      | retransmit | b | 0 = no retransmit (default) 1 = retransmitted |
    +-------+-----+----------------------+------------+---+-----------------------------------------------+
    | 71    | 1   | Spare                |            | x | Not used                                      |
    +-------+-----+----------------------+------------+---+-----------------------------------------------+
    | 72-81 | 10  | Designated Area Code | dac        | u | Unsigned integer                              |
    +-------+-----+----------------------+------------+---+-----------------------------------------------+
    | 82-87 | 6   | Functional ID        | fid        | u | Unsigned integer                              |
    +-------+-----+----------------------+------------+---+-----------------------------------------------+
    | 88    | 920 | Data                 | data       | d | Binary data May be shorter than 920 bits.     |
    +-------+-----+----------------------+------------+---+-----------------------------------------------+
 */
package com.rinus.messages;
import com.rinus.decoder.Payload;
import com.rinus.decoder.BitString;
import com.rinus.messages.Message;

public class Message6 extends Message {
    private int seqno; //numero de secuencia
    private int dest_mmsi;
    private int retransmit;
    private int dac; //codigo de area designada
    private int fid;
    private BitString data;

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
        data = payload.getLastBits();

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
    public BitString getData() {return data;}
    public void setData(BitString data) {
        this.data = data;
    }
}
