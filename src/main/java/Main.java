import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Main {
    static int MAX_BUF = 65536;
    public static void main(String []args)
    {
        StringBuilder msg = new StringBuilder(); //mensaje actual
        Decoder decoder = new Decoder();
        /*
        Decoder.decode("!AIVDM,2,1,6,B,58156:T2>weuKLpwB20t<D4r098DE`F222222216>PF8A6KT0>5QDPH0lUF`,0*62!AIVDM,2,2,6,B,88888888880,2*21");
        Decoder.decode("!AIVDM,2,1,8,A,569r?PP000000000000P4UQDr3737000000000000000040000000000,0*08!AIVDM,2,2,8,A,000000000000000,2*2C");
        Decoder.decode("!AIVDM,1,1,,A,13HOI:0P0000VOHLCnHQKwvL05Ip,0*23");
        Decoder.decode("!AIVDM,1,1,,A,402=3g1uiposjOP71jSQ1sA026sd,0*03");
        Decoder.decode("!AIVDM,1,1,,B,9oVAuAI5;rRRv2OqTi?1uoP?=a@1,0*74");
        Decoder.decode("!AIVDM,1,1,,A,:5Ovc200B=5H,0*43");
        Decoder.decode("!AIVDM,1,1,,A,15MgK45P3@G?fl0E`JbR0OwT0@MS,0*4E");
        Decoder.decode("!AIVDM,1,1,,B,K815>P8=5EikdUet,0*6B");
        Decoder.decode("!AIVDM,1,1,,B,16f=0bhP0MI8B7p:uI0pfoUh00ST,0*6F");
        Decoder.decode("!AIVDM,1,1,0,B,Evkb9Mq1WV:VQ4Ph94c@6;Q@1a@;ShvA==bd`00003vP000,2*65");
        Decoder.decode("!AIVDM,1,1,,B,H3tlF0TTT0000000004>M3Q<4h0,2*23");
        //Decoder.decode("!AIVDM,1,1,,B,65Ps:8=:0MjP0420<4U>1@E=B10i>04<fp0,2*23");
        Decoder.decode("!AIVDM,2,1,9,A,ENk`sO70VQ97aRh1T0W72V@611@=FVj<;V5d@00003v,0*50!AIVDM,2,2,9,A,P0<M0,0*3E");
        Decoder.decode("!AIVDM,1,1,6,A,F030owj2N2P6Ubib@=4q35b10000,0*74");
        Decoder.decode("!AIVDM,1,1,,B,G02:KpP1R`sn@291njF00000900,2*1C");
        Decoder.decode("!AIVDM,1,1,6,B,Dh3OwjhflnfpLIF9HM1F9HMaF9H,2*3E");
        Decoder.decode("!AIVDM,2,1,6,B,55ArUT02:nkG<I8GB20nuJ0p5HTu>0hT9860TV16000006420BDi@E53,0*33!AIVDM,2,2,6,B,1KUDhH888888880,2*6A");
        Decoder.decode("!AIVDM,2,1,6,A,55NOvQP1u>QIL@O??SL985`u>0EQ18E=>222221J1p`884i6N344Sll1@m80,0*0C!AIVDM,2,2,6,A,TRA1iH88880,2*6F");
        Decoder.decode("!AIVDM,2,1,1,A,<02PeAPpIkF06B?=PB?31P3?>DB?<rP@<51C5P3?>D13DPB?31P3?>DB,0*13!AIVDM,2,2,1,A,?<P?>PF86P381>>5<PoqP6?BP=1>41D?BIPB5@?BD@,4*66");
        Decoder.decode("!AIVDM,1,1,,A,602a4KU29NHP04<0@0,4*78");
         */

        //Benchmark.run();

        Socket socket = null;
        BufferedInputStream in;
        InputStream is;
        // Creamos un socket en el lado cliente, enlazado con un
        // servidor que está en la misma máquina que el cliente
        // y que escucha en el puerto 4444
        try {
            socket = new Socket("192.168.100.55", 2000);
            is = socket.getInputStream();
            in = new BufferedInputStream(is);
        } catch(IOException e) {
            try {
                assert socket != null;
                socket.close();
            } catch(IOException e2) {
                System.err.println("El socket no esta cerrado: " + e2);
            }
            System.out.println("No esta abierto el socket: " + e.getMessage());
            return;
        }
        while(true) {
            try {
                String recv = readInputStream(in);
                msg.append(recv);
                assert recv != null;
                if(recv.length() > 2)
                {
                    boolean cr = recv.charAt(recv.length() - 1) == '\n';
                    boolean lf = recv.charAt(recv.length() - 2) == '\r';
                    if(cr || lf)
                    {
                        decoder.decode(msg);
                        msg = new StringBuilder();
                    }
                }

            } catch(IOException e) {
                System.out.println("Conexion perdida");
                break;
            }
        }
        try	{
            is.close();
            in.close();
            socket.close();
        } catch (Exception ignored) {
        }
    }
    private static String readInputStream(BufferedInputStream _in) throws IOException {
        String data = "";
        int s = _in.read();
        if(s == -1) return null;
        data += (char) s;
        int len = _in.available();
        if(len > 0) {
            byte[] byteData = new byte[len];
            int r = _in.read(byteData);
            if(r == -1) return null;
            data += new String(byteData);
        }
        return data;
    }

}
