package Lesson1.ProblemH;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int L = input.nextInt();
        int x1 = input.nextInt();
        int v1 = input.nextInt();
        int x2 = input.nextInt();
        int v2 = input.nextInt();


        if (v1 == 0 && v2 == 0) {
            if (x1 == x2) {
                System.out.println("YES");
                System.out.println(0);
                System.exit(0);
            } else if ((x1 == (L - x2) || (x2 == (L - x1)))) {
                System.out.println("YES");
                System.out.println(0);
                System.exit(0);
            } else {
                System.out.println("NO");
                System.exit(0);
            }
        }

        if (v1 < 0 && v2 < 0) {
            // переопределим
            // x1 >= x2
            if (x2 >= x1) {
                int temp1 = x1;
                int temp2 = v1;
                x1 = x2;
                v1 = v2;
                x2 = temp1;
                v2 = temp2;
            }


            Double answer1 = null;
            Double answer2 = null;
            if (v1 != v2) {
                answer1 = ((double) (x2 - x1) / (v1 - v2));
            }

            if (v1 != -v2) {
                answer2 = ((double) (x2 + x1) / (-v2 - v1));
            }

            if (answer1 == null && answer2 == null) {
                System.out.println("NO");
            } else if (answer1 == null) {
                System.out.println("YES");
                System.out.println(answer2);
            } else {
                System.out.println("YES");
                System.out.println(answer1);
            }

        } else {
            if (x2 >= x1) {
                int temp1 = x1;
                int temp2 = v1;
                x1 = x2;
                v1 = v2;
                x2 = temp1;
                v2 = temp2;
            }

            if (v1 != v2) {
                double answer1 = (double) (x1 - x2) / (v2 - v1);
                if (v1 != -v2) {
                    double answer2 = (double) (L - x1 - x2) / (v1 + v2);

                    if (answer1 < 0 && answer2 < 0) {
                        System.out.println("NO");
                    } else if (answer1 < 0) {
                        System.out.println("YES");
                        System.out.println(answer2);
                    } else {
                        System.out.println("YES");
                        System.out.println(answer1);
                    }
                }
            } else {
                double answer = (double) (L - x1 - x2) / (v1 + v2);
                if (answer < 0) {
                    System.out.println("NO");
                } else {
                    System.out.println("YES");
                    System.out.println(answer);
                }
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
