import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
public class Decoder {

    /*
    Estructura de mensaje AIS
    !AIVDM,1,1,,B,4025;PAuho;N>0NJbfMRhNA00D3l,0*66
    Campo 1 tipo de mensaje
    Campo 2 numero de partes en las que fue dividido este mensaje
    Campo 3 que fragmento de mensaje es
    Campo 4 id secuencial
    Campo 5 codigo del canal de radio
    Campo 6 paylooad
    Campo 7 numero de bits de rellano
    Campo 8 checksum
     */


    /**
     * inicializa las estructuras de datos que se usaran en la decodificacion
     */
    public static void init() {
        if (sixBitAsciiTable == null) {
            sixBitAsciiTable = new HashMap<>();
            messages = new HashMap<>();
            String table = "0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVW`abcdefghijklmnopqrstuvw";
            for (int idx = 0; idx < table.length(); idx++) {
                sixBitAsciiTable.put(table.charAt(idx), idx);
            }
            messages.put(1,  Message1_2_3.class);
            messages.put(2,  Message1_2_3.class);
            messages.put(3,  Message1_2_3.class);
            messages.put(4,  Message4.class);
            messages.put(5,  Message5.class);
            messages.put(6,  Message6.class);
            messages.put(7,  Message7.class);
            messages.put(8,  Message8.class);
            messages.put(9,  Message9.class);
            messages.put(10, Message10.class);
            messages.put(11, Message4.class);
            messages.put(12, Message12.class);
            messages.put(13, Message7.class);
            messages.put(14, Message14.class);
            messages.put(16, Message16.class);
            messages.put(17, Message17.class);
            messages.put(18, Message18.class);
            messages.put(19, Message19.class);
            messages.put(20, Message20.class);
            messages.put(21, Message21.class);
            messages.put(22, Message22.class);
            messages.put(23, Message23.class);
            messages.put(24, Message24.class); //tipo de mensaje 25 y 26 extremadente raro, no se han visto desde 2011 en el aishub
            messages.put(27, Message27.class);
        }
    }
    public static void decode(String nmeaMsg) {
        if (nmeaMsg.matches("^(!AIVDM,\\d,\\d,.*,[abAB]?,.*,\\d\\*.*){1,2}$")) {
            var sentences = nmeaMsg.split("!AIVDM");
            var payload = new Payload();
            for(var sentence: sentences)
            {
                if(sentence.length() > 0)
                {
                    var fields = sentence.split(",");
                    payload.append(fields[5], sixBitAsciiTable);
                }
            }
            int msgType = payload.getMsgtype();
            if(!messages.containsKey(msgType))
            {
                System.out.printf("Tipo de mensaje %d no soportado\n", msgType);
                return;
            }
            Message msg = null;
            try {
                msg = messages.get(msgType).getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                assert msg != null;
                msg.parse(payload);
                System.out.println(msg.toJson());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.printf("Formato inválido: %s\n", nmeaMsg);
        }
    }
    private static HashMap<Character, Integer> sixBitAsciiTable = null; //estructura de datos para asociar a cada caracter con su equivalente a entero de 6 bits
    private static HashMap<Integer, Class<? extends Message>> messages;
}