package Lesson1.ProblemB;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        String s1 = input.nextLine();
        String[] point1 = s1.split(":");
        int g1 = Integer.parseInt(point1[0]);
        int g2 = Integer.parseInt(point1[1]);

        String s2 = input.nextLine();
        String[] point2 = s2.split(":");
        int p1 = Integer.parseInt(point2[0]);
        int p2 = Integer.parseInt(point2[1]);

        int choice = input.nextInt();
        int sum1 = g1 + p1;
        int sum2 = g2 + p2;
        if (sum1 > sum2) {
            System.out.println(0);
            System.exit(0);
        }
        int minDifference = Math.abs(sum1 - sum2);
        if (choice == 1) {

            if (minDifference + p1 > g2) {
                System.out.println(minDifference);
            } else {
                System.out.println(minDifference + 1);
            }
        } else {
            if (g1 > p2) {
                System.out.println(minDifference);
            } else {
                System.out.println(minDifference + 1);
            }
        }
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
