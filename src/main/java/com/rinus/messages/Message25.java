/*
    +-------+-------+-----------------------+------------+---+---------------------------+
    | Field | Len   | Description           | Member     | T | Units                     |
    +-------+-------+-----------------------+------------+---+---------------------------+
    | 0-5   | 6     | Message Type          | type       | u | Constant: 25              |
    +-------+-------+-----------------------+------------+---+---------------------------+
    | 6-7   | 2     | Repeat Indicator      | repeat     | u | As in CNB                 |
    +-------+-------+-----------------------+------------+---+---------------------------+
    | 8-37  | 30    | MMSI                  | mmsi       | u | 9 digits                  |
    +-------+-------+-----------------------+------------+---+---------------------------+
    | 38    | 1     | Destination indicator | addressed  | b | 0=broadcast, 1=addressed. |
    +-------+-------+-----------------------+------------+---+---------------------------+
    | 39    | 1     | Binary data flag      | structured | b | See below                 |
    +-------+-------+-----------------------+------------+---+---------------------------+
    | 40    | 0/30  | Destination MMSI      | dest_mmsi  | u | Message destination       |
    +-------+-------+-----------------------+------------+---+---------------------------+
    | ?     | 0/16  | Application ID        | app_id     | u | Unsigned integer          |
    +-------+-------+-----------------------+------------+---+---------------------------+
    | ?     | 0-128 | Data                  | data       | d | Binary data               |
    +-------+-------+-----------------------+------------+---+---------------------------+
 */
package com.rinus.messages;
import com.rinus.decoder.Payload;
import com.rinus.decoder.BitString;
import com.rinus.messages.Message;

public class Message25 extends Message {
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
    public int getAddresed() {return addresed;}
    public void setAddresed(int addresed) {this.addresed = addresed;}
    public int getStructured() {return structured;}
    public void setStructured(int structured) {this.structured = structured;}
    public int getDest_mmsi() {return dest_mmsi;}
    public void setDest_mmsi(int dest_mmsi) {this.dest_mmsi = dest_mmsi;}
    public int getApp_id() {return app_id;}
    public void setApp_id(int app_id) {this.app_id = app_id;}
    public BitString getData() {return data;}
    public void setData(BitString data) {this.data = data;}
}
