public class Message14 extends Message {
    private BinaryString text;
    @Override
    public void parse(Payload payload) throws NMEAMessageException
    {
        super.parse(payload);
        payload.getData().getNbits(2); //sin usar
        text = payload.getData().getNbits(payload.size());
    }
    public BinaryString getText() {
        return text;
    }
    public void setText(BinaryString text) {
        this.text = text;
    }
}
