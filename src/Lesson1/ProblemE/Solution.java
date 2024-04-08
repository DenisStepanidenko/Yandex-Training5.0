package Lesson1.ProblemE;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        long n = input.nextLong();
        long k = input.nextLong();
        long d = input.nextLong();
        StringBuilder answer = new StringBuilder(String.valueOf(n));
        int fixOstatok = (int) (10 % k);
        int ostatokNumber = (int) (n % k);
        for (int i = 0; i < d; i++) {
            int currentOstatok = fixOstatok * ostatokNumber;
            boolean flag = true;
            // теперь нужно найти цифру, чтобы сумма остатков делилось на k
            for (int m = 0; m < 10; m++) {
                if ((currentOstatok + (m % k)) % k == 0) {
                    // добавим цифру m
                    answer.append(m);
                    ostatokNumber = 0;
                    flag = false;
                    break;
                }
            }
            if(flag){
                System.out.println(-1);
                System.exit(0);
            }
        }
        System.out.println(answer);
    }

    static class Reader extends PrintWriter {

        private BufferedReader r;
        private StringTokenizer st;
        // standard input

        public Reader() {
            this(System.in, System.out);
        }

        public Reader(InputStream i, OutputStream o) {
            super(o);
            r = new BufferedReader(new InputStreamReader(i));
        }
        // USACO-style file input

        public Reader(String problemName) throws IOException {
            super(problemName + ".out");
            r = new BufferedReader(new FileReader(problemName));
        }

        // returns null if no more input
        String nextLine() {
            String str = "";
            try {
                str = r.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        public String next() {
            try {
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(r.readLine());
                }
                return st.nextToken();
            } catch (Exception e) {
            }
            return null;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {

            return Long.parseLong(next());
        }
    }
}
