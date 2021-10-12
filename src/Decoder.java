import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
public class Decoder {
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
            messages.put(24, Message24.class);
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
                    var tokens = sentence.split(",");
                    payload.append(tokens[5], sixBitAsciiTable);
                }
            }
            int msgType = payload.getMsgtype();
            Message msg = null;
            try {
                msg = messages.get(msgType).getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            System.out.println("---------------------------------------------");
            try {
                assert msg != null;
                msg.parse(payload);
                msg.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("---------------------------------------------");
        } else {
            System.out.println("Formato invalido");
        }
    }
    private static HashMap<Character, Integer> sixBitAsciiTable = null; //estructura de datos para asociar a cada caracter con su equivalente a entero de 6 bits
    private static HashMap<Integer, Class<? extends Message>> messages;
}