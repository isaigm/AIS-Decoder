import java.util.HashMap;

public class Payload {
    private BinaryString data = null;
    Payload()
    {
        data = new BinaryString();
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
     * @param sixBitAsciiTable arreglo de equivalencias entre caracter ascii en 6 bits a numero entero
     */
    void append(String payload, HashMap<Character, Integer> sixBitAsciiTable)
    {
        for (int idx = 0; idx < payload.length(); idx++) {
            int sixBit = sixBitAsciiTable.get(payload.charAt(idx));
            data.append(BinaryString.fromNumber(sixBit).toString());
        }
    }
    public int size()
    {
        return data.size();
    }
}