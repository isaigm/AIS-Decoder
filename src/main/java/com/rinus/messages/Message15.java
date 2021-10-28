/*
    +---------+-----+---------------------+-----------+---+-------------------------------+
    | Field   | Len | Description         | Member    | T | Units                         |
    +---------+-----+---------------------+-----------+---+-------------------------------+
    | 0-5     | 6   | Message Type        | type      | u | Constant: 15                  |
    +---------+-----+---------------------+-----------+---+-------------------------------+
    | 6-7     | 2   | Repeat Indicator    | repeat    | u | As in Common Navigation Block |
    +---------+-----+---------------------+-----------+---+-------------------------------+
    | 8-37    | 30  | Source MMSI         | mmsi      | u | 9 decimal digits              |
    +---------+-----+---------------------+-----------+---+-------------------------------+
    | 38-39   | 2   | Spare               |           | x | Not used                      |
    +---------+-----+---------------------+-----------+---+-------------------------------+
    | 40-69   | 30  | Interrogated MMSI   | mmsi1     | u | 9 decimal digits              |
    +---------+-----+---------------------+-----------+---+-------------------------------+
    | 70-75   | 6   | First message type  | type1_1   | u | Unsigned integer              |
    +---------+-----+---------------------+-----------+---+-------------------------------+
    | 76-87   | 12  | First slot offset   | offset1_1 | u | Unsigned integer              |
    +---------+-----+---------------------+-----------+---+-------------------------------+
    | 88-89   | 2   | Spare               |           | x | Not used                      |
    +---------+-----+---------------------+-----------+---+-------------------------------+
    | 90-95   | 6   | Second message type | type1_2   | u | Unsigned integer              |
    +---------+-----+---------------------+-----------+---+-------------------------------+
    | 96-107  | 12  | Second slot offset  | offset1_2 | u | Unsigned integer              |
    +---------+-----+---------------------+-----------+---+-------------------------------+
    | 108-109 | 2   | Spare               |           | x | Not used                      |
    +---------+-----+---------------------+-----------+---+-------------------------------+
    | 110-139 | 30  | Interrogated MMSI   | mmsi2     | u | 9 decimal digits              |
    +---------+-----+---------------------+-----------+---+-------------------------------+
    | 140-145 | 6   | First message type  | type2_1   | u | Unsigned integer              |
    +---------+-----+---------------------+-----------+---+-------------------------------+
    | 146-157 | 12  | First slot offset   | offset2_1 | u | Unsigned integer              |
    +---------+-----+---------------------+-----------+---+-------------------------------+
    | 158-159 | 2   | Spare               |           | x | Not used                      |
    +---------+-----+---------------------+-----------+---+-------------------------------+
 */
package com.rinus.messages;
import com.rinus.decoder.Payload;
import com.rinus.messages.Message;

public class Message15 extends Message {
    private int mmsi1;
    private int type1_1;
    private int offset1_1;
    private int type1_2;
    private int offset1_2;
    private int mmsi2;
    private int type2_1;
    private int offset2_1;
    @Override
    public void parse(Payload payload) throws NMEAMessageException {
        if(payload.size() != 88 || payload.size() != 160 || payload.size() != 110 || payload.size() != 112)
        {
            throw new NMEAMessageException("Mensaje tipo 15: longitud erronea");
        }
        super.parse(payload);
        payload.getNextNbits(2); //sin uso
        mmsi1 = payload.getNextNbits(30).toInteger();
        type1_1 = payload.getNextNbits(6).toInteger();
        offset1_1 = payload.getNextNbits(12).toInteger();
        payload.getNextNbits(2); //sin uso
        type1_2 = payload.getNextNbits(6).toInteger();
        offset1_2 = payload.getNextNbits(12).toInteger();
        payload.getNextNbits(2); //sin usar
        mmsi2 = payload.getNextNbits(30).toInteger();
        type2_1 = payload.getNextNbits(6).toInteger();
        offset2_1 = payload.getNextNbits(12).toInteger();

    }
}
