package Lesson2.ProblemB;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int n = input.nextInt();
        int k = input.nextInt();
        int[] priceForDays = new int[n];
        for (int i = 0; i < n; i++) {
            int currentPrice = input.nextInt();
            priceForDays[i] = currentPrice;
        }

        // так как ограничения небольшие, то сделаем перебор всех вариантов просто
        int answer = 0;
        for (int i = 0; i < priceForDays.length; i++) {
            // покупаем за текущую цену и смотрим в каждые из следующих k дней
            int current = priceForDays[i];
            for (int j = 1; j <= k && (j + i) < priceForDays.length; j++) {
                if (priceForDays[i + j] - current > answer) {
                    answer = priceForDays[i + j] - current;
                }
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
