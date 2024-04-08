package Lesson1.ProblemF;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int n = input.nextInt();
        int previousParity; // будет хранится чётность числа. 0 - чётное, 1 - нечётное
        int x = input.nextInt();
        previousParity = x % 2;
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            x = input.nextInt();
            if(previousParity == 0){
                // до этого было чётное число
                if(x % 2 == 0){
                    answer.append("x");
                }
                else{
                    previousParity = 1;
                    answer.append("+");
                }
            }
            else{
                // до этого было нечётное число
                if(x % 2 == 0){
                    answer.append("+");
                    previousParity = 1;
                }
                else{
                    previousParity = 1;
                    answer.append("x");
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
