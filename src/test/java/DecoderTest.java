
import com.rinus.decoder.Decoder;
import com.rinus.messages.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DecoderTest {

    static Decoder decoder = new Decoder();

    @Test
    public void testMsg1_2_3()
    {
        String msg = "!AIVDM,1,1,,A,15ACeB001KI83M@:w;:b`8P<00SQ,0*2A";
        Message1_2_3 result = (Message1_2_3) decoder.decode(msg);
        assertAll(() -> assertEquals(result.getMmsi(),353693000 ),
                () -> assertEquals(result.getMsgType(), 1),
                () -> assertEquals(result.getStatus(), "En marcha con motor"),
                () -> assertEquals(result.getRepeat(), 0),
                () -> assertEquals(result.getTurn(), 0),
                () -> assertEquals(result.getSpeed(), 9.1f),
                () -> assertEquals(result.getLongitude(),-96.107666f),
                () -> assertEquals(result.getLatitude(),19.20135f),
                () -> assertEquals(result.getCourse(), 272.0),
                () -> assertEquals(result.getHeading(), 272),
                () -> assertEquals(result.getSecond(), 6),
                () -> assertEquals(result.getRadio(), 2273),
                () -> assertEquals(result.getManeuver(), "No disponible (por defecto)"));
    }
    @Test
    public void testMsg4()
    {
        String msg = "!AIVDM,1,1,,B,4h3Owoiuiq000rdhR6G>oQ?020S:,0*10";
        Message4 result = (Message4) decoder.decode(msg);
        assertAll(() -> assertEquals(result.getMmsi(),3669983),
                () -> assertEquals(result.getMsgType(), 4),
                () -> assertEquals(result.getRepeat(), 3),
                () -> assertEquals(result.getYear(), 2012),
                () -> assertEquals(result.getMonth(), 7),
                () -> assertEquals(result.getDay(), 18),
                () -> assertEquals(result.getHour(), 0),
                () -> assertEquals(result.getMinute(), 0),
                () -> assertEquals(result.getSecond(), 0),
                () -> assertEquals(result.getLongitude(), -74.108475f),
                () -> assertEquals(result.getLatitude(), 40.60139f),
                () -> assertEquals(result.getEpfd(), "No definido"),
                () -> assertEquals(result.getRaim(), 1),
                () -> assertEquals(result.getRadio(), 2250));
    }
    @Test
    public void testMsg5()
    {
        String msg = "!AIVDM,2,1,6,B,58156:T2>weuKLpwB20t<D4r098DE`F222222216>PF8A6KT0>5QDPH0lUF`,0*62!AIVDM,2,2,6,B,88888888880,2*21";
        Message5 result = (Message5) decoder.decode(msg);
        assertAll(() -> assertEquals(result.getMmsi(),538002986),
                () -> assertEquals(result.getMsgType(), 5),
                () -> assertEquals(result.getRepeat(), 0),
                () -> assertEquals(result.getAis_version(), 1),
                () -> assertEquals(result.getImo(), 9371359),
                () -> assertEquals(result.getCallsign(), "V7NO4"),
                () -> assertEquals(result.getShipname(), "OCEAN BREEZE"),
                () -> assertEquals(result.getShiptype(), "De carga"),
                () -> assertEquals(result.getTo_bow(), 116),
                () -> assertEquals(result.getTo_stern(), 22),
                () -> assertEquals(result.getTo_port(), 8),
                () -> assertEquals(result.getTo_starboard(), 17),
                () -> assertEquals(result.getEpfd(), "GPS"),
                () -> assertEquals(result.getMonth(), 9),
                () -> assertEquals(result.getDay(), 23),
                () -> assertEquals(result.getHour(), 4),
                () -> assertEquals(result.getMinute(), 0),
                () -> assertEquals(result.getDraught(), 5.6f),
                () -> assertEquals(result.getDestination(), "VERA CRUZ"),
                () -> assertEquals(result.getDte(), 0));
    }
    @Test
    public void testMsg6()
    {
        String msg = "!AIVDM,1,1,,A,602a4KU29NHP04<0@0,4*78";
        Message6 result = (Message6) decoder.decode(msg);
        assertAll(() -> assertEquals(result.getMmsi(),2770030),
                () -> assertEquals(result.getMsgType(), 6),
                () -> assertEquals(result.getRepeat(), 0),
                () -> assertEquals(result.getSeqno(), 1),
                () -> assertEquals(result.getDest_mmsi(), 277445000),
                () -> assertEquals(result.getRetransmit(), 0),
                () -> assertEquals(result.getDac(), 1),
                () -> assertEquals(result.getFid(), 3),
                () -> assertEquals(result.getData().toString(), "00000000010000000000"));
    }
    @Test
    public void testMsg7()
    {
        String msg = "!AIVDM,1,1,,A,7jvPoD@mMq;U,0*09";
        Message7 result = (Message7) decoder.decode(msg);
        assertAll(() -> assertEquals(result.getMmsi(),199767889),
                () -> assertEquals(result.getMsgType(), 7),
                () -> assertEquals(result.getRepeat(), 3),
                () -> assertEquals(result.getMmsi1(), 224257209),
                () -> assertEquals(result.getMmsiseq1(), 1),
                () -> assertEquals(result.getMmsi2(), 0),
                () -> assertEquals(result.getMmsiseq2(), 0),
                () -> assertEquals(result.getMmsi3(), 0),
                () -> assertEquals(result.getMmsiseq3(), 0));
    }
    @Test
    public void testMsg8()
    {
        String msg = "!AIVDM,1,1,,A,8@2<HV@0BkLN:0frqMPaQPtBRRIrwwejwwwwwwwwwwwwwwwwwwwwwwwwwt0,2*34";
        Message8 result = (Message8) decoder.decode(msg);
        assertAll(() -> assertEquals(result.getMmsi(),2300057),
                () -> assertEquals(result.getMsgType(), 8),
                () -> assertEquals(result.getRepeat(), 1),
                () -> assertEquals(result.getDac(), 1),
                () -> assertEquals(result.getFid(), 11),
                () -> assertEquals(result.getPayload().getData().toString(), "0011011100011110001010000000101110111010111001011101100000101001100001100000111100010010100010100010011001111010111111111111101101110010111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111100000000"));
    }
    @Test
    public void testMsg9()
    {
        String msg = "!AIVDM,1,1,,B,9oVAuAI5;rRRv2OqTi?1uoP?=a@1,0*74";
        Message9 result = (Message9) decoder.decode(msg);
        assertAll(() -> assertEquals(result.getMmsi(),509902149),
                () -> assertEquals(result.getMsgType(), 9),
                () -> assertEquals(result.getRepeat(), 3),
                () -> assertEquals(result.getAltitude(), 2324),
                () -> assertEquals(result.getSpeed(),762),
                () -> assertEquals(result.getLongitude(),35.6012f),
                () -> assertEquals(result.getLatitude(),-11.22934f),
                () -> assertEquals(result.getSecond(), 30),
                () -> assertEquals(result.getDte(), 1),
                () -> assertEquals(result.getAssigned(), 1),
                () -> assertEquals(result.getRaim(), 1),
                () -> assertEquals(result.getRadio(),431105));
    }
    @Test
    public void testMsg10()
    {
        String msg = "!AIVDM,1,1,,B,:CFlQi3vqjM8;W98tPnSK`g<W9cqE;h<tP,1*12";
        Message10 result = (Message10) decoder.decode(msg);
        assertAll(() -> assertEquals(result.getMmsi(),225255876),
                () -> assertEquals(result.getMsgType(), 10),
                () -> assertEquals(result.getRepeat(), 1),
                () -> assertEquals(result.getDest_mmsi(), 1069140434));
    }
    @Test
    public void testMsg12()
    {
        String msg = "!AIVDM,2,1,1,A,<02PeAPpIkF06B?=PB?31P3?>DB?<rP@<51C5P3?>D13DPB?31P3?>DB,0*13 NOLINT!AIVDM,2,2,1,A,?<P?>PF86P381>>5<PoqP6?BP=1>41D?BIPB5@?BD@,4*66";
        Message12 result = (Message12) decoder.decode(msg);
        assertAll(() -> assertEquals(result.getMmsi(),2633030),
                () -> assertEquals(result.getMsgType(), 12),
                () -> assertEquals(result.getRepeat(), 0),
                () -> assertEquals(result.getDest_mmsi(), 236572000),
                () -> assertEquals(result.getSeqno(), 0),
                () -> assertEquals(result.getText(), "FROM ROCA CONTROL: PLEASE CONTACT ROCA CONTROL ON VHF CHANNEL 79 FOR MANDATORY REPORT"));
    }
    @Test
    public void testMsg14()
    {
        String msg = "!AIVDM,1,1,,A,>>M@rl1<59B1@E=@0000000,2*0D";
        Message14 result = (Message14) decoder.decode(msg);
        assertAll(() -> assertEquals(result.getMmsi(),970210000),
                () -> assertEquals(result.getMsgType(), 14),
                () -> assertEquals(result.getRepeat(), 0),
                () -> assertEquals(result.getText(), "SART TEST"));
    }
    @Test
    public void testMsg15()
    {
        String msg = "!AIVDM,1,1,,A,?7VN8RKqwwTo=foWV7iwt?vLp,4*44";
        Message15 result = (Message15) decoder.decode(msg);
        assertAll(() -> assertEquals(result.getMmsi(),510101641),
                () -> assertEquals(result.getMsgType(), 15),
                () -> assertEquals(result.getRepeat(), 0),
                () -> assertEquals(result.getMmsi1(), 1048575565),
                () -> assertEquals(result.getType1_1(), 51),
                () -> assertEquals(result.getOffset1_1(), 1773),
                () -> assertEquals(result.getType1_2(), 39),
                () -> assertEquals(result.getMmsi2(), 134156281),
                () -> assertEquals(result.getType2_1(), 51),
                () -> assertEquals(result.getOffset2_1(), 0));
    }
}