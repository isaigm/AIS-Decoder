/*
    +-------+-----+------------------+------------+---+------------------------------------------------------------+
    | Field | Len | Description      | Member     | T | Units                                                      |
    +-------+-----+------------------+------------+---+------------------------------------------------------------+
    | 0-5   | 6   | Message Type     | type       | u | Constant: 12                                               |
    +-------+-----+------------------+------------+---+------------------------------------------------------------+
    | 6-7   | 2   | Repeat Indicator | repeat     | u | As in Common Navigation Block                              |
    +-------+-----+------------------+------------+---+------------------------------------------------------------+
    | 8-37  | 30  | Source MMSI      | mmsi       | u | 9 decimal digits                                           |
    +-------+-----+------------------+------------+---+------------------------------------------------------------+
    | 38-39 | 2   | Sequence Number  | seqno      | u | Unsigned integer 0-3                                       |
    +-------+-----+------------------+------------+---+------------------------------------------------------------+
    | 40-69 | 30  | Destination MMSI | dest_mmsi  | u | 9 decimal digits                                           |
    +-------+-----+------------------+------------+---+------------------------------------------------------------+
    | 70    | 1   | Retransmit flag  | retransmit | b | 0 = no retransmit (default), 1 = retransmitted             |
    +-------+-----+------------------+------------+---+------------------------------------------------------------+
    | 71    | 1   | Spare            |            | x | Not used                                                   |
    +-------+-----+------------------+------------+---+------------------------------------------------------------+
    | 72    | 936 | Text             | text       | t | 1-156 chars of six-bit text. May be shorter than 936 bits. |
    +-------+-----+------------------+------------+---+------------------------------------------------------------+
 */
package com.rinus.messages;
import com.rinus.decoder.Payload;
import com.rinus.messages.Message;

public class Message12 extends Message {
    private int seqno;
    private int dest_mmsi;
    private int retransmit;
    private String text;

    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        seqno = payload.getNextNbits(2).toInteger();
        dest_mmsi = payload.getNextNbits(30).toInteger();
        retransmit = payload.getNextNbits(1).toInteger();
        payload.getNextNbits(1);
        text = payload.getLastBits().toSixBitAscii();
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
