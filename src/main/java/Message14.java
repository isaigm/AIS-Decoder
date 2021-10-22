public class Message14 extends Message {
    private BitString text;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        payload.getNextNbits(2); //sin usar
        text = payload.getNextNbits(payload.size() - payload.getCurrentPos());
    }
    public BitString getText() {
        return text;
    }
    public void setText(BitString text) {
        this.text = text;
    }
}
