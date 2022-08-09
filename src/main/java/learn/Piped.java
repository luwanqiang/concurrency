package learn;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 *  管道流示例：
 */
public class Piped {

    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        // 将输入输出流进行连接，否则在使用时会抛出IOException
        out.connect(in);

        // main线程与print线程管道连接
        new Thread(new Print(in), "printThread").start();
        int receive = 0;
        try {
            while ((receive = System.in.read()) != -1) {
                // 往管道流内写入
                out.write(receive);
            }
        } finally {
            out.close();
        }
    }

    static class Print implements Runnable {
        private PipedReader in;
        public Print (PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receive = 0;
            try {
                while ((receive = in.read()) != -1) {
                    System.out.println((char) receive);
                }
            } catch (IOException e) {

            }
        }
    }
}
