package decoder;

public class BitString {

    private final StringBuilder buffer;
    public BitString(String bits)
    {
        buffer = new StringBuilder(bits);
    }
    public BitString()
    {
        buffer = new StringBuilder();
    }
    public void append(String bits)
    {
        buffer.append(bits);
    }
    public void append(int bit)
    {
        buffer.append(bit);
    }
    /**
     * Convierte esta cadena binaria a su representacion en decimal
     * @return regresa un entero, que es el valor en decimal de la cadena binaria
     */
    public int toInteger()
    {
        int res = 0;
        for (int idx = 0; idx < buffer.length(); idx++) {
            int bit = buffer.charAt(idx) - '0';
            res += bit << (buffer.length() - 1 - idx);
        }
        return res;
    }

    /**
     * @return regresa el resultado de convertir esta cadena binaria a un numero entero con signo
     */
    public int toSignedInt()
    {
        if(buffer.charAt(0) == '1')
        {

            return -1 * twosComplement().toInteger();
        }
        return toInteger();
    }
    /**
     * Calcula el complemento a 2 de esta cadena binaria invirtiendo todos sus bits y sumando 1 al resultado
     * @return regresa esta misma cadena en complemento a 2
     */
    public BitString twosComplement()
    {
        for (int idx = 0; idx < buffer.length(); idx++) {
            int neg = buffer.charAt(idx) - '0';
            if (neg == 1) {
                neg = 0;
            } else {
                neg = 1;
            }
            buffer.setCharAt(idx, (char) (neg + '0'));
        }
        int carry = 1;
        for (int idx = buffer.length() - 1; idx >= 0; idx--) {
            int bit = buffer.charAt(idx) - '0';
            if (carry + bit > 1) {
                buffer.setCharAt(idx, '0');
            } else {
                buffer.setCharAt(idx, '1');
                break;
            }
        }
        return this;
    }
    /**
     * Convierte un numero a su representacion binaria tomando solamante 6 bits
     * @param n un numero entero
     * @return regresa una cadena binaria
     */
    public static BitString fromNumber(int n)
    {
        BitString bstring = new BitString();
        for (int i = 0; i < 6; i++) {
            int bit = (n >> (5 - i)) & 1;
            bstring.append(bit);
        }
        return bstring;
    }
    /**
     * Convierte esta cadena binaria a su representacion ascii en 6 bits
     * @return regresa la cadena ascii
     */
    public String toSixBitAscii() {
        int left = 0;
        var text = new StringBuilder();
        for (int idx = 0; idx + 6 < buffer.length(); idx += 6) {
            int i = new BitString(buffer.substring(left, idx + 6)).toInteger();
            left = idx + 6;
            char ch = asciiTable.charAt(i);
            if(ch != '@')
            {
                text.append(ch);
            }
        }
        return text.toString().trim();
    }
    public BitString substring(int start, int end)
    {
        return new BitString(buffer.substring(start, end));
    }

    public int size()
    {
        return buffer.length();
    }
    @Override
    public String toString()
    {
        return buffer.toString();
    }
    private static final String asciiTable = "@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_ !\"#$%&'()*+,-./0123456789:;<=>?";
}
