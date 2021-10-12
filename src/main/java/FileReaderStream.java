import java.io.*;

public class FileReaderStream {
    static void run()
    {
        File file = new File("/home/radar/Descargas/nmea-sample");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder multilineSentence = new StringBuilder();
            int cnt = -1;
            String line;
            while ((line = br.readLine()) != null) {
                var fields = line.split(",");
                int segments = Integer.parseInt(fields[1]);
                if(segments > 1){
                    multilineSentence.append(line);
                    if(cnt == -1)
                    {
                        cnt = segments;
                    }
                    cnt--;
                    if(cnt == 0)
                    {
                        cnt = -1;
                        Decoder.decode(multilineSentence.toString());
                        multilineSentence = new StringBuilder();
                    }
                }else
                {
                    Decoder.decode(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
