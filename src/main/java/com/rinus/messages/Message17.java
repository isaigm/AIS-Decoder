/*
    +--------+-----+------------------+--------+----+-------------------------------+
    | Field  | Len | Description      | Member | T  | Units                         |
    +--------+-----+------------------+--------+----+-------------------------------+
    | 0-5    | 6   | Message Type     | type   | u  | Constant: 17                  |
    +--------+-----+------------------+--------+----+-------------------------------+
    | 6-7    | 2   | Repeat Indicator | repeat | u  | As in Common Navigation Block |
    +--------+-----+------------------+--------+----+-------------------------------+
    | 8-37   | 30  | Source MMSI      | mmsi   | u  | 9 decimal digits              |
    +--------+-----+------------------+--------+----+-------------------------------+
    | 38-39  | 2   | Spare            |        | x  | Not used                      |
    +--------+-----+------------------+--------+----+-------------------------------+
    | 40-57  | 18  | Longitude        | lon    | I1 | Signed: minutes/10            |
    +--------+-----+------------------+--------+----+-------------------------------+
    | 58-74  | 17  | Latitude         | lat    | I1 | Signed: minutes/10            |
    +--------+-----+------------------+--------+----+-------------------------------+
    | 75-79  | 5   | Spare            |        | x  | Not used - reserved           |
    +--------+-----+------------------+--------+----+-------------------------------+
    | 80-815 | 736 | Payload          | data   | d  | DGNSS correction data         |
    +--------+-----+------------------+--------+----+-------------------------------+
 */
package com.rinus.messages;
import com.rinus.decoder.Payload;
import com.rinus.decoder.BitString;
import com.rinus.messages.Message;

public class Message17 extends Message {
    private float longitude;
    private float latitude;
    private BitString data;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        payload.getNextNbits(2); //sin usar
        longitude = payload.getNextNbits(18).toSignedInt() * 0.0001f / 60;;
        latitude = payload.getNextNbits(17).toSignedInt() * 0.0001f / 60;
        payload.getNextNbits(5); //sin usar
        data = payload.getLastBits();
    }
    public float getLongitude() {
        return longitude;
    }
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
    public float getLatitude() {
        return latitude;
    }
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
    public BitString getData() {
        return data;
    }
    public void setData(BitString data) {
        this.data = data;
    }
}
