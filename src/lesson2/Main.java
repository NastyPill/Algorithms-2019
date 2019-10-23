package lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String s = in.next();
        String s1 = in.next();
        out.println(longestCommonSubstring(s, s1));
    }

    static public String longestCommonSubstring(String firs, String second) {
        int[][] d = new int[firs.length() + 1][second.length() + 1];
        int max = -1, pos = 0;
        for (int i = 1; i < firs.length(); i++) {
            for (int j = 1; j < second.length(); j++) {
                if(firs.charAt(i) == second.charAt(j)) {
                    d[i][j] = d[i - 1][j - 1] + 1;
                }
                if(d[i][j] > max) {
                    max = d[i][j];
                    pos = i;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = max - 1; i >= 0; i--) {
            sb.append(firs.charAt(pos - i));
        }
        return sb.toString();
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}