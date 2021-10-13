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
        payload.getData().getNbits(2); //sin usar
        offset1 = payload.getData().getNbits(12).toInteger();
        number1 = payload.getData().getNbits(4).toInteger();
        timeout1 = payload.getData().getNbits(3).toInteger();
        increment1 = payload.getData().getNbits(11).toInteger();
        if(payload.size() >= 30)
        {
            offset2 = payload.getData().getNbits(12).toInteger();
            number2 = payload.getData().getNbits(4).toInteger();
            timeout2 = payload.getData().getNbits(3).toInteger();
            increment2 = payload.getData().getNbits(11).toInteger();
        }
        if(payload.size() >= 30){
            offset3 = payload.getData().getNbits(12).toInteger();
            number3 = payload.getData().getNbits(4).toInteger();
            timeout3 = payload.getData().getNbits(3).toInteger();
            increment3 = payload.getData().getNbits(11).toInteger();
        }
        if(payload.size() >= 30){
            offset4 = payload.getData().getNbits(12).toInteger();
            number4 = payload.getData().getNbits(4).toInteger();
            timeout4 = payload.getData().getNbits(3).toInteger();
            increment4 = payload.getData().getNbits(11).toInteger();
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
