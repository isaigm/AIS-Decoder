/*
    +---------+-----+------------------+------------+---+-------------------------------+
    | Field   | Len | Description      | Member     | T | Units                         |
    +---------+-----+------------------+------------+---+-------------------------------+
    | 0-5     | 6   | Message Type     | type       | u | Constant: 20                  |
    +---------+-----+------------------+------------+---+-------------------------------+
    | 6-7     | 2   | Repeat Indicator | repeat     | u | As in CNB                     |
    +---------+-----+------------------+------------+---+-------------------------------+
    | 8-37    | 30  | MMSI             | mmsi       | u | 9 decimal digits              |
    +---------+-----+------------------+------------+---+-------------------------------+
    | 38-39   | 2   | Spare            |            | x | Not used                      |
    +---------+-----+------------------+------------+---+-------------------------------+
    | 40-51   | 12  | Offset number 1  | offset1    | u | Reserved offset number        |
    +---------+-----+------------------+------------+---+-------------------------------+
    | 52-55   | 4   | Reserved slots   | number1    | u | Consecutive slots             |
    +---------+-----+------------------+------------+---+-------------------------------+
    | 56-58   | 3   | Time-out         | timeout1   | u | Allocation timeout in minutes |
    +---------+-----+------------------+------------+---+-------------------------------+
    | 59-69   | 11  | Increment        | increment1 | u | Repeat increment              |
    +---------+-----+------------------+------------+---+-------------------------------+
    | 70-81   | 12  | Offset number 2  | offset2    | u | Reserved offset number        |
    +---------+-----+------------------+------------+---+-------------------------------+
    | 82-85   | 4   | Reserved slots   | number2    | u | Consecutive slots             |
    +---------+-----+------------------+------------+---+-------------------------------+
    | 86-88   | 3   | Time-out         | timeout2   | u | Allocation timeout in minutes |
    +---------+-----+------------------+------------+---+-------------------------------+
    | 89-99   | 11  | Increment        | increment2 | u | Repeat increment              |
    +---------+-----+------------------+------------+---+-------------------------------+
    | 100-111 | 12  | Offset number 3  | offset3    | u | Reserved offset number        |
    +---------+-----+------------------+------------+---+-------------------------------+
    | 112-115 | 4   | Reserved slots   | number3    | u | Consecutive slots             |
    +---------+-----+------------------+------------+---+-------------------------------+
    | 116-118 | 3   | Time-out         | timeout3   | u | Allocation timeout in minutes |
    +---------+-----+------------------+------------+---+-------------------------------+
    | 119-129 | 11  | Increment        | increment3 | u | Repeat increment              |
    +---------+-----+------------------+------------+---+-------------------------------+
    | 130-141 | 12  | Offset number 4  | offset4    | u | Reserved offset number        |
    +---------+-----+------------------+------------+---+-------------------------------+
    | 142-145 | 4   | Reserved slots   | number4    | u | Consecutive slots             |
    +---------+-----+------------------+------------+---+-------------------------------+
    | 146-148 | 3   | Time-out         | timeout4   | u | Allocation timeout in minutes |
    +---------+-----+------------------+------------+---+-------------------------------+
    | 149-159 | 11  | Increment        | increment4 | u | Repeat increment              |
    +---------+-----+------------------+------------+---+-------------------------------+
 */
package messages;
import decoder.Payload;
public class Message20 extends Message {
    private int offset1;
    private int number1;
    private int timeout1;
    private int increment1;
    private int offset2;
    private int number2;
    private int timeout2;
    private int increment2;
    private int offset3;
    private int number3;
    private int timeout3;
    private int increment3;
    private int offset4;
    private int number4;
    private int timeout4;
    private int increment4;
    @Override
    public void parse(Payload payload) throws NMEAMessageException {
        super.parse(payload);
        payload.getNextNbits(2); //sin usar
        offset1 = payload.getNextNbits(12).toInteger();
        number1 = payload.getNextNbits(4).toInteger();
        timeout1 = payload.getNextNbits(3).toInteger();
        increment1 = payload.getNextNbits(11).toInteger();
        if(payload.getRemainingSize() >= 30)
        {
            offset2 = payload.getNextNbits(12).toInteger();
            number2 = payload.getNextNbits(4).toInteger();
            timeout2 = payload.getNextNbits(3).toInteger();
            increment2 = payload.getNextNbits(11).toInteger();
        }
        if(payload.getRemainingSize() >= 30){
            offset3 = payload.getNextNbits(12).toInteger();
            number3 = payload.getNextNbits(4).toInteger();
            timeout3 = payload.getNextNbits(3).toInteger();
            increment3 = payload.getNextNbits(11).toInteger();
        }
        if(payload.getRemainingSize()>= 30){
            offset4 = payload.getNextNbits(12).toInteger();
            number4 = payload.getNextNbits(4).toInteger();
            timeout4 = payload.getNextNbits(3).toInteger();
            increment4 = payload.getNextNbits(11).toInteger();
        }
    }
    @Override
    public void print()
    {
        super.print();
        System.out.printf("Offset1: %d\n", offset1);
        System.out.printf("Number1: %d\n", number1);
        System.out.printf("Timeout1: %d\n", timeout1);
        System.out.printf("Increment1: %d\n", increment1);

        System.out.printf("Offset2: %d\n", offset2);
        System.out.printf("Number2: %d\n", number2);
        System.out.printf("Timeout2: %d\n", timeout2);
        System.out.printf("Increment2: %d\n", increment2);

        System.out.printf("Offset3: %d\n", offset3);
        System.out.printf("Number3: %d\n", number3);
        System.out.printf("Timeout3: %d\n", timeout3);
        System.out.printf("Increment3: %d\n", increment3);

        System.out.printf("Offset4: %d\n", offset4);
        System.out.printf("Number4: %d\n", number4);
        System.out.printf("Timeout4: %d\n", timeout4);
        System.out.printf("Increment4: %d\n", increment4);
    }
}
