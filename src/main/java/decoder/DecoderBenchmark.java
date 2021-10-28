package decoder;
import java.io.*;

public class DecoderBenchmark {
    public static void run()
    {
        File file = new File("/home/radar/Descargas/nmea-sample");
        Decoder decoder = new Decoder();
        try {
            long startTime = System.nanoTime();
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
                    if(--cnt == 0)
                    {
                        cnt = -1;
                        decoder.decode(multilineSentence);
                        multilineSentence = new StringBuilder();
                    }
                }else
                {
                    decoder.decode(line);
                }
            }
            long endTime = System.nanoTime();
            System.out.printf("Time elapsed: %f seconds\n", (endTime - startTime) / 1000000000.0f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
