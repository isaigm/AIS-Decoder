import java.util.HashMap;

public class Payload {
    private BitString data = null;
    private int currentPos = 0; //esta variable sirve para ir obteniendo cada campo del payload
    //cuando se requieren k bits, se hace currentPos += k para asi cuando se vuelvan a requerir más bits se obtengan
    //a partir de donde se quedó currentPos, por ejemplo bits = data.substring(currentPos, currentPos + k)
    Payload()
    {
        data = new BitString();
        String table = "0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVW`abcdefghijklmnopqrstuvw";
        for (int idx = 0; idx < table.length(); idx++) {
            sixBitAsciiTable.put(table.charAt(idx), idx);
        }
    }
    public BitString getData()
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
            data.append(BitString.fromNumber(sixBit).toString());
        }
    }
    /**
     * Obtiene los n bits del BitString data a partir de currentPos
     * @param n bits
     * @return cadena binaria con los n primeros bits a partir de @param
     */
    public BitString getNextNbits(int n)
    {
        if(currentPos + n <= data.size())
        {
            BitString bits = data.substring(currentPos, currentPos + n);
            currentPos += n;
            return bits;
        }
        return new BitString("");
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