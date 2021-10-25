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
