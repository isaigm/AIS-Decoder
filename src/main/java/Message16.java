/*
    +---------+-----+--------------------+------------+---+-------------------------------+
    | Field   | Len | Description        | Member     | T | Units                         |
    +---------+-----+--------------------+------------+---+-------------------------------+
    | 0-5     | 6   | Message Type       | type       | u | Constant: 16                  |
    +---------+-----+--------------------+------------+---+-------------------------------+
    | 6-7     | 2   | Repeat Indicator   | repeat     | u | As in Common Navigation Block |
    +---------+-----+--------------------+------------+---+-------------------------------+
    | 8-37    | 30  | Source MMSI        | mmsi       | u | 9 decimal digits              |
    +---------+-----+--------------------+------------+---+-------------------------------+
    | 38-39   | 2   | Spare              |            | x | Not used                      |
    +---------+-----+--------------------+------------+---+-------------------------------+
    | 40-69   | 30  | Destination A MMSI | mmsi1      | u | 9 decimal digits              |
    +---------+-----+--------------------+------------+---+-------------------------------+
    | 70-81   | 12  | Offset A           | offset1    | u | See [IALA]                    |
    +---------+-----+--------------------+------------+---+-------------------------------+
    | 82-91   | 10  | Increment A        | increment1 | u | See [IALA]                    |
    +---------+-----+--------------------+------------+---+-------------------------------+
    | 92-121  | 30  | Destination B MMSI | mmsi2      | u | 9 decimal digits              |
    +---------+-----+--------------------+------------+---+-------------------------------+
    | 122-133 | 12  | Offset B           | offset2    | u | See [IALA]                    |
    +---------+-----+--------------------+------------+---+-------------------------------+
    | 134-143 | 10  | Increment B        | increment2 | u | See [IALA]                    |
    +---------+-----+--------------------+------------+---+-------------------------------+
 */
public class Message16 extends Message {
    private int mmsi1;
    private int offset1;
    private int increment1;
    private int mmsi2;
    private int offset2;
    private int increment2;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        if(payload.size() != 96 || payload.size() != 144) throw new NMEAMessageException("Mensaje tipo 16: longitud erronea");
        boolean twoStation = payload.size() == 144;
        super.parse(payload);
        payload.getNextNbits(2); //sin usar
        mmsi1 = payload.getNextNbits(30).toInteger();
        offset1 = payload.getNextNbits(12).toInteger();
        increment1 = payload.getNextNbits(10).toInteger();
        if(twoStation)
        {
            mmsi2 = payload.getNextNbits(30).toInteger();
            offset2 = payload.getNextNbits(12).toInteger();
            increment2 = payload.getNextNbits(10).toInteger();
        }
    }
    public int getMmsi1() {
        return mmsi1;
    }
    public void setMmsi1(int mmsi1) {
        this.mmsi1 = mmsi1;
    }
    public int getOffset1() {
        return offset1;
    }
    public void setOffset1(int offset1) {
        this.offset1 = offset1;
    }
    public int getIncrement1() {
        return increment1;
    }
    public void setIncrement1(int increment1) {
        this.increment1 = increment1;
    }
    public int getMmsi2() {
        return mmsi2;
    }
    public void setMmsi2(int mmsi2) {
        this.mmsi2 = mmsi2;
    }
    public int getOffset2() {
        return offset2;
    }
    public void setOffset2(int offset2) {
        this.offset2 = offset2;
    }
    public int getIncrement2() {
        return increment2;
    }
    public void setIncrement2(int increment2) {
        this.increment2 = increment2;
    }
}
