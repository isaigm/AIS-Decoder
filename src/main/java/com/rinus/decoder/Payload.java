package com.rinus.decoder;
public class Payload {
    private final BitString data;
    private int currentPos = 0; //esta variable sirve para ir obteniendo cada campo del payload
    //cuando se requieren k bits, se hace currentPos += k para asi cuando se vuelvan a requerir más bits se obtengan
    //a partir de donde se quedó currentPos, por ejemplo bits = data.substring(currentPos, currentPos + k)
    public Payload()
    {
        data = new BitString();
    }
    public Payload(BitString data)
    {
        this.data = data;
    }
    public BitString getData()
    {
        return data;
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

    /**
     * regresa la cantidad de bits que quedan por obtener
     * @return
     */
    public int getRemainingSize()
    {
        return data.size() - currentPos;
    }

    /**
     * Regresa los ultimos bits del BitString data a partir de currentPos
     * @return
     */
    public BitString getLastBits()
    {
        return getNextNbits(data.size() - currentPos);
    }
    public int size()
    {
        return data.size();
    }
}