public class BinaryString {
    private final StringBuilder sb;
    public BinaryString(String bits)
    {
        sb = new StringBuilder(bits);
    }
    public BinaryString()
    {
        sb = new StringBuilder();
    }
    public void append(String bits)
    {
        sb.append(bits);
    }
    public void append(int bit)
    {
        sb.append(bit);
    }
    /**
     * Convierte esta cadena binaria a su representacion en decimal
     * @return regresa un entero, que es el valor en decimal de la cadena binaria
     */
    public int toInteger()
    {
        int res = 0;
        for (int idx = 0; idx < sb.length(); idx++) {
            int bit = sb.charAt(idx) - '0';
            res += bit << (sb.length() - 1 - idx);
        }
        return res;
    }

    /**
     * @return regresa el resultado de convertir esta cadena binaria a un numero entero con signo
     */
    public float toSignedInt()
    {
        if(sb.charAt(0) == '1')
        {
            deleteFirstNbits(1);
            return -1 * twosComplement().toInteger();
        }
        return toInteger();
    }
    /**
     * Calcula el complemento a 2 de esta cadena binaria invirtiendo todos sus bits y sumando 1 al resultado
     * @return regresa esta misma cadena en complemento a 2
     */
    public BinaryString twosComplement()
    {
        for (int idx = 0; idx < sb.length(); idx++) {
            int neg = sb.charAt(idx) - '0';
            if (neg == 1) {
                neg = 0;
            } else {
                neg = 1;
            }
            sb.setCharAt(idx, (char) (neg + '0'));
        }
        int carry = 1;
        for (int idx = sb.length() - 1; idx >= 0; idx--) {
            int bit = sb.charAt(idx) - '0';
            if (carry + bit > 1) {
                sb.setCharAt(idx, '0');
            } else {
                sb.setCharAt(idx, '1');
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
    public static BinaryString fromNumber(int n)
    {
        BinaryString bstring = new BinaryString();
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
        for (int idx = 0; idx + 6 < sb.length(); idx += 6) {
            int i = new BinaryString(sb.substring(left, idx + 6)).toInteger();
            left = idx + 6;
            if(asciiTable.charAt(i) != '@')
            {
                text.append(asciiTable.charAt(i));
            }
        }
        return text.toString();
    }
    public BinaryString substring(int start, int end)
    {
        return new BinaryString(sb.substring(start, end));
    }
    /**
     * Obtiene los primeros n bits de esta cadena
     * @param n bits
     * @return cadena binaria con los primero n bits
     */
    public BinaryString getNbits(int n)
    {
        String nbits = sb.substring(0, n);
        deleteFirstNbits(n);
        return new BinaryString(nbits);
    }
    public void deleteFirstNbits(int n)
    {
        sb.delete(0, n);
    }
    public int size()
    {
        return sb.length();
    }
    @Override
    public String toString()
    {
        return sb.toString();
    }
    private static final String asciiTable = "@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_ !\"#$%&'()*+,-./0123456789:;<=>?";
}
