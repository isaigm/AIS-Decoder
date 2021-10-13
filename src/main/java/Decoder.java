import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.function.Supplier;

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
            messages.put(1,  Message1_2_3::new);
            messages.put(2,  Message1_2_3::new);
            messages.put(3,  Message1_2_3::new);
            messages.put(4,  Message4::new);
            messages.put(5,  Message5::new);
            messages.put(6,  Message6::new);
            messages.put(7,  Message7::new);
            messages.put(8,  Message8::new);
            messages.put(9,  Message9::new);
            messages.put(10, Message10::new);
            messages.put(11, Message4::new);
            messages.put(12, Message12::new);
            messages.put(13, Message7::new);
            messages.put(14, Message14::new);
            messages.put(16, Message16::new);
            messages.put(17, Message17::new);
            messages.put(18, Message18::new);
            messages.put(19, Message19::new);
            messages.put(20, Message20::new);
            messages.put(21, Message21::new);
            messages.put(22, Message22::new);
            messages.put(23, Message23::new);
            messages.put(24, Message24::new); //tipo de mensaje 25 y 26 extremadentes raro, no se han visto desde 2011 en el aishub
            messages.put(27, Message27::new);
        }
    }
    public static void decode(String nmeaMsg) {
        if (nmeaMsg.matches("^(!AIVDM,\\d,\\d,.*,[abAB]?,.*,\\d\\*.*\r?\n){1,2}$")) {
            var sentences = nmeaMsg.split("!AIVDM");
            boolean isMultilineSentence = false;

            System.out.println(nmeaMsg);
            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
            var payload = new Payload();
            for(var sentence: sentences)
            {
                if(sentence.length() > 0)
                {
                    var fields = sentence.split(",");
                    payload.append(fields[5], sixBitAsciiTable);
                    int segments = Integer.parseInt(fields[1]);
                    if(segments == 1)
                    {
                        Payload p = new Payload();
                        p.append(fields[5], sixBitAsciiTable);
                        _decode(p);

                    }else isMultilineSentence = true;
                }
            }
            if(isMultilineSentence)
            {
                _decode(payload);
            }
        } else {
            System.out.printf("Formato inválido: %s\n", nmeaMsg);
        }
    }
    private static void _decode(Payload payload)
    {
        int msgType = payload.getMsgtype();
        if(isValidMsgType(msgType))
        {
            Message msg = messages.get(msgType).get();
            try {
                msg.parse(payload);
                System.out.println(msg.toJson());
            } catch (NMEAMessageException e) {
                e.printStackTrace();
            }
        }
    }
    private static boolean isValidMsgType(int msgtype)
    {
        return messages.containsKey(msgtype);
    }
    private static HashMap<Character, Integer> sixBitAsciiTable = null; //estructura de datos para asociar a cada caracter con su equivalente a entero de 6 bits
    private static HashMap<Integer, Supplier<Message>> messages;
}