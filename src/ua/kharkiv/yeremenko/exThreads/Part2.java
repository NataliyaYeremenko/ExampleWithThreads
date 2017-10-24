package ua.kharkiv.yeremenko.exThreads;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Part2 {

    public static void main(String[] args) throws Exception {
        InputStream stdIn = System.in;
        ByteArrayInputStream bais = new ByteArrayInputStream(System.lineSeparator().getBytes());
        bais.skip(System.lineSeparator().length());
        System.setIn(bais);
        Thread thread = Spam.main();
        Thread.sleep(5000);
        bais.reset();
        thread.join();
        System.setIn(stdIn);
    }
}
