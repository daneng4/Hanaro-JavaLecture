package JavaLecture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {

    private static final BufferedReader bufferedReader = new BufferedReader(
        new InputStreamReader(System.in));


    public InputReader() {
    }

    public static String readInput() throws IOException {
        return bufferedReader.readLine();
    }
}
