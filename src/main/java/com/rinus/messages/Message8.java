/*
    +-------+-----+----------------------+--------+---+--------------------------------------------+
    | Field | Len | Description          | Member | T | Units                                      |
    +-------+-----+----------------------+--------+---+--------------------------------------------+
    | 0-5   | 6   | Message Type         | type   | u | Constant: 8                                |
    +-------+-----+----------------------+--------+---+--------------------------------------------+
    | 6-7   | 2   | Repeat Indicator     | repeat | u | As in Common Navigation Block              |
    +-------+-----+----------------------+--------+---+--------------------------------------------+
    | 8-37  | 30  | Source MMSI          | mmsi   | u | 9 decimal digits                           |
    +-------+-----+----------------------+--------+---+--------------------------------------------+
    | 38-39 | 2   | Spare                |        | x | Not used                                   |
    +-------+-----+----------------------+--------+---+--------------------------------------------+
    | 40-49 | 10  | Designated Area Code | dac    | u | Unsigned integer                           |
    +-------+-----+----------------------+--------+---+--------------------------------------------+
    | 50-55 | 6   | Functional ID        | fid    | u | Unsigned integer                           |
    +-------+-----+----------------------+--------+---+--------------------------------------------+
    | 56    | 952 | Data                 | data   | d | Binary data, May be shorter than 952 bits. |
    +-------+-----+----------------------+--------+---+--------------------------------------------+
 */
package com.rinus.messages;
import com.rinus.decoder.Payload;
import com.rinus.messages.Message;

public class Message8 extends Message {
    private int dac;
    private int fid;
    private Payload data;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        payload.getNextNbits(2); //sin usar
        dac =  payload.getNextNbits(10).toInteger();
        fid =  payload.getNextNbits(6).toInteger();
        data = new Payload(payload.getLastBits());
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("Código de área de asignada: %d\n", dac);
        System.out.printf("FID: %d\n", fid);
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


}
