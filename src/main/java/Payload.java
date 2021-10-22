import java.util.HashMap;

public class Payload {
    private BinaryString data = null;
    private int currentPos = 0;
    Payload()
    {
        data = new BinaryString();
        String table = "0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVW`abcdefghijklmnopqrstuvw";
        for (int idx = 0; idx < table.length(); idx++) {
            sixBitAsciiTable.put(table.charAt(idx), idx);
        }
    }
    public BinaryString getData()
    {
        return data;
    }
    public int getMsgtype()
    {
        return data.substring(0, 6).toInteger();
    }
    /**
     * Agrega el payload en ascii al binaryString data ya convertido en bits
     * @param payload payload en ascii
     */
    void append(String payload)
    {
        for (int idx = 0; idx < payload.length(); idx++) {
            int sixBit = sixBitAsciiTable.get(payload.charAt(idx));
            data.append(BinaryString.fromNumber(sixBit).toString());
        }
    }
    /**
     * Obtiene los n bits de esta cadena a partir de la posicion actual
     * @param n bits
     * @return cadena binaria con los primero n bits
     */
    public BinaryString getNextNbits(int n)
    {
        if(currentPos + n <= data.size())
        {
            BinaryString bits = data.substring(currentPos, currentPos + n);
            currentPos += n;
            return bits;
        }
        return new BinaryString("");
    }
    public int getCurrentPos()
    {
        return currentPos;
    }
    public int size()
    {
        return data.size();
    }
    private final HashMap<Character, Integer> sixBitAsciiTable = new HashMap<>();; //estructura de datos para asociar a cada caracter con su equivalente a entero de 6 bits
}