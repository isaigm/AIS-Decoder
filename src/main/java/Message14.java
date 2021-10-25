/*
    +-------+-----+------------------+--------+---+------------------------------------------------------------+
    | Field | Len | Description      | Member | T | Units                                                      |
    +-------+-----+------------------+--------+---+------------------------------------------------------------+
    | 0-5   | 6   | Message Type     | type   | u | Constant: 14                                               |
    +-------+-----+------------------+--------+---+------------------------------------------------------------+
    | 6-7   | 2   | Repeat Indicator | repeat | u | As in Common Navigation Block                              |
    +-------+-----+------------------+--------+---+------------------------------------------------------------+
    | 8-37  | 30  | Source MMSI      | mmsi   | u | 9 decimal digits                                           |
    +-------+-----+------------------+--------+---+------------------------------------------------------------+
    | 38-39 | 2   | Spare            |        | x | Not used                                                   |
    +-------+-----+------------------+--------+---+------------------------------------------------------------+
    | 40    | 968 | Text             | text   | t | 1-161 chars of six-bit text. May be shorter than 968 bits. |
    +-------+-----+------------------+--------+---+------------------------------------------------------------+
 */
public class Message14 extends Message {
    private String text;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        payload.getNextNbits(2); //sin usar
        text = payload.getLastBits().toSixBitAscii();
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
