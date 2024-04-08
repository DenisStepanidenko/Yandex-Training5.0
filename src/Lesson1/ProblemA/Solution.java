package Lesson1.ProblemA;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        long p = input.nextLong();
        long v = input.nextLong();
        long leftVasya = p - v;
        long rightVasya = p + v;


        long q = input.nextLong();
        long m = input.nextLong();
        long leftMasha = q - m;
        long rightMasha = q + m;

        long left = Math.min(leftVasya, leftMasha);
        long right = Math.max(rightVasya, rightMasha);
        long answer = 0;
        for (long i = left; i <= right; i++) {
            if ((i >= leftVasya && i <= rightVasya) && (i >= leftMasha && i <= rightMasha)) {
                answer++;
            } else if ((i >= leftVasya && i <= rightVasya)) {
                answer++;
            } else if ((i >= leftMasha && i <= rightMasha)) {
                answer++;
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
