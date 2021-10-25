/*
    +-------+-----+------------------+-----------+---+-------------------------------+
    | Field | Len | Description      | Member    | T | Encoding                      |
    +-------+-----+------------------+-----------+---+-------------------------------+
    | 0-5   | 6   | Message Type     | type      | u | Constant: 10                  |
    +-------+-----+------------------+-----------+---+-------------------------------+
    | 6-7   | 2   | Repeat Indicator | repeat    | u | As in Common Navigation Block |
    +-------+-----+------------------+-----------+---+-------------------------------+
    | 8-37  | 30  | Source MMSI      | mmsi      | u | 9 decimal digits              |
    +-------+-----+------------------+-----------+---+-------------------------------+
    | 38-39 | 2   | Spare            |           | x | Not used                      |
    +-------+-----+------------------+-----------+---+-------------------------------+
    | 40-69 | 30  | Destination MMSI | dest_mmsi | u | 9 decimal digits              |
    +-------+-----+------------------+-----------+---+-------------------------------+
    | 70-71 | 2   | Spare            |           | x | Not used                      |
    +-------+-----+------------------+-----------+---+-------------------------------+
 */
public class Message10 extends Message {
    private int dest_mmsi;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        payload.getNextNbits(2);//sin usar
        dest_mmsi =  payload.getNextNbits(30).toInteger();
        payload.getNextNbits(2);//sin usar
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("MMSI destino: %d\n", dest_mmsi);
    }
    public int getDest_mmsi() {
        return dest_mmsi;
    }

    public void setDest_mmsi(int dest_mmsi) {
        this.dest_mmsi = dest_mmsi;
    }
}
