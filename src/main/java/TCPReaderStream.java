import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TCPReaderStream {
    private final BufferedInputStream in;

    public TCPReaderStream(InputStream stream) {
        in = new BufferedInputStream(stream);
    }

    /**
     * Se decofidican las tramas que se iran obteniendo mediante el BufferedInputStream
     */
    public void run() {
        StringBuilder msg = new StringBuilder();
        Decoder decoder = new Decoder();
        while (true) {
            String recv;
            try {
                recv = readInputStream();
                if (recv == null || recv.length() < 2)
                    continue;
                msg.append(recv);
                boolean cr = recv.charAt(recv.length() - 1) == '\n'; // al final de una trama se envian los caracteres
                                                                     // retorno de carro \n y salto de linea \r, aunque podria no estar el salto de linea
                boolean lf = recv.charAt(recv.length() - 2) == '\r';
                StringBuilder multilineSentence = new StringBuilder();
                int cnt = -1;
                if (cr || lf) {
                    // entran aqui la mayoria de mensajes, sin embargo puede pasar que se envian
                    // sucesivamente varios mensajes sin sus respectivos caracteres de
                    // fin de linea o salto de carro, por lo que msg puede tener varios mensajes de
                    // una sola linea o multilinea hasta que se reciba \n o \r
                    var lines = msg.toString().split("!AIVDM");
                    for (var line : lines) {
                        if (line.length() > 0) {
                            var fields = line.split(",");
                            int segments = Integer.parseInt(fields[1]); // indica en cuantas partes se ha dividido el
                                                                        // mensaje
                            if (segments > 1) {
                                multilineSentence.append("!AIVDM").append(line); // aqui se toma la suposicion de que una vez se envie un
                                                                // mensaje multilinea, las demas partes llegaran en
                                                                // orden
                                if (cnt == -1) {
                                    cnt = segments;
                                }
                                if (--cnt == 0) {
                                    cnt = -1;
                                    decoder.decode(multilineSentence);
                                    multilineSentence = new StringBuilder();
                                }
                            } else {
                                decoder.decode(new StringBuilder("!AIVDM").append(line));
                            }
                        }
                    }
                    msg = new StringBuilder();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String readInputStream() throws IOException {
        String data = "";
        int s = in.read();
        if (s == -1)
            return null;
        data += (char) s;
        int len = in.available();
        if (len > 0) {
            byte[] byteData = new byte[len];
            int r = in.read(byteData);
            if (r == -1)
                return null;
            data += new String(byteData);
        }
        return data;
    }
}
