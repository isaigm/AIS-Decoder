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
    public Decoder()
    {
        if(sixBitAsciiTable == null)
        {
            sixBitAsciiTable = new HashMap<>();
            messages = new HashMap<>();
            String table = "0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVW`abcdefghijklmnopqrstuvw";
            for (int idx = 0; idx < table.length(); idx++) {
                sixBitAsciiTable.put(table.charAt(idx), idx);
            }
            messages.put(1,  Message1_2_3::new); //mensaje 1, 2 y 3 tienen la misma estructura
            messages.put(2,  Message1_2_3::new);
            messages.put(3,  Message1_2_3::new);
            messages.put(4,  Message4::new);
            messages.put(5,  Message5::new);
            messages.put(6,  Message6::new);
            messages.put(7,  Message7::new);
            messages.put(8,  Message8::new);
            messages.put(9,  Message9::new);
            messages.put(10, Message10::new);
            messages.put(11, Message4::new); //misma estructura que el mensaje 4
            messages.put(12, Message12::new);
            messages.put(13, Message7::new); //misma estructura que el mensaje 7
            messages.put(14, Message14::new);
            messages.put(15, Message15::new);
            messages.put(16, Message16::new);
            messages.put(17, Message17::new);
            messages.put(18, Message18::new);
            messages.put(19, Message19::new);
            messages.put(20, Message20::new);
            messages.put(21, Message21::new);
            messages.put(22, Message22::new);
            messages.put(23, Message23::new);
            messages.put(24, Message24::new); //tipo de mensaje 25 y 26 extremadentes raros, no se han visto desde 2011 en el aishub
            messages.put(27, Message27::new);
        }
    }
    public Message decode(String nmeaMsg) {
        if (nmeaMsg.matches("^(!AIVDM,\\d,\\d,.*,[abAB]?,.*,\\d\\*.*\r?\n?)+$")) {
            System.out.println(nmeaMsg.trim());
            var sentences = nmeaMsg.split("!AIVDM");
            if(sentences.length > 2)
            {
                try {
                    return handleMultilineSentence(sentences);
                } catch (FormatException e) {
                    e.printStackTrace();
                }
            }else
            {
                Payload payload = new Payload();
                var fields = nmeaMsg.split(",");
                payload.append(fields[5], sixBitAsciiTable);
                return __decode(payload);
            }
        } else {
            System.out.printf("Formato inválido: %s\n", nmeaMsg);
        }
        return null;
    }
    public void decode(StringBuilder msg)
    {
        decode(msg.toString());
    }
    private Message __decode(Payload payload)
    {
        int msgType = payload.getMsgtype();
        if(isValidMsgType(msgType))
        {
            Message msg = messages.get(msgType).get();
            try {
                msg.parse(payload);
                System.out.println(msg.toJson());
                return msg;
            } catch (NMEAMessageException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public Message handleMultilineSentence(String[] sentences) throws FormatException
    {
        var payload = new Payload();
        int sgmt = 0;
        for(var sentence: sentences)
        {
            if(sentence.length() > 0)
            {
                var fields = sentence.split(",");
                int segments = Integer.parseInt(fields[1]);
                int currSgmt = Integer.parseInt(fields[2]);
                if(segments == 1) {
                    throw new FormatException("Formato inválido, se espera un mensaje multilínea");
                }
                if(currSgmt - 1 != sgmt)
                {
                    throw new FormatException("Formato inválido, el mensaje está en desorden");
                }

                payload.append(fields[5], sixBitAsciiTable);
                sgmt = currSgmt;
            }
        }
        return __decode(payload);
    }
    private boolean isValidMsgType(int msgtype)
    {
        return messages.containsKey(msgtype);
    }
    public static HashMap<Character, Integer> sixBitAsciiTable = null; //estructura de datos para asociar a cada caracter con su equivalente a entero de 6 bits
    private static HashMap<Integer, Supplier<Message>> messages = null;
}