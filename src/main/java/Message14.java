public class Message14 extends Message {
    private BinaryString text;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        payload.getNextNbits(2); //sin usar
        text = payload.getNextNbits(payload.size() - payload.getCurrentPos());
    }
    public BinaryString getText() {
        return text;
    }
    public void setText(BinaryString text) {
        this.text = text;
    }
}
