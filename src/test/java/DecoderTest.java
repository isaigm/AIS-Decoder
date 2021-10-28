import decoder.Decoder;
import messages.Message1_2_3;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DecoderTest {

    static Decoder decoder = new Decoder();

    @Test
    public void testMSG1_2_3()
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
}