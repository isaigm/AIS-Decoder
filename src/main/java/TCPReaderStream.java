import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TCPReaderStream {
    private final BufferedInputStream in;

    public TCPReaderStream(InputStream stream) {
        in = new BufferedInputStream(stream);
    }

    public void run() {
        StringBuilder msg = new StringBuilder();
        Decoder decoder = new Decoder();
        while (true) {
            String recv = null;
            try {
                recv = readInputStream(in);
                msg.append(recv);
                if (recv == null || recv.length() < 2)
                    continue;
                boolean cr = recv.charAt(recv.length() - 1) == '\n'; // al final de una trama se envian los caracteres
                                                                     // retorno de carro \n y salto de linea \r
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
                                multilineSentence.append(line); // aqui se toma la suposicion de que una vez se envie un
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
                                decoder.decode(line);
                            }
                        }
                        msg = new StringBuilder();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String readInputStream(BufferedInputStream _in) throws IOException {
        String data = "";
        int s = _in.read();
        if (s == -1)
            return null;
        data += (char) s;
        int len = _in.available();
        if (len > 0) {
            byte[] byteData = new byte[len];
            int r = _in.read(byteData);
            if (r == -1)
                return null;
            data += new String(byteData);
        }
        return data;
    }
}
