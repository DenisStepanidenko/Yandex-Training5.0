package Lesson2.ProblemD;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int[][] board = new int[9][]; // создаём поле из массивов (наша доска)

        for (int i = 0; i < 9; i++) {
            int[] current = new int[9];
            Arrays.fill(current, 0);
            board[i] = current;
        }
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            board[x][y]++;
        }
        int answer = 0;
        for (int i = 1; i < 9; i++) {
            // нужно для начала периметр фигуры из клеток на строке i
            int[] currentRow = board[i];
            answer += getPerimeter(currentRow);
            if (i != 1) {
                // нужно ещё учесть клеточки выше
                for (int j = 1; j < currentRow.length; j++) {
                    if (currentRow[j] == 0) {
                    } else {
                        if (board[i - 1][j] == 1) {
                            answer -= 2;
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }

    public static int getPerimeter(int[] currentRow) {
        int perimeter = 0;
        boolean flag = false;
        for (int i = 1; i < currentRow.length; i++) {
            if (currentRow[i] == 0) {
                flag = false;
            } else {
                perimeter += 4;
                if (flag) {
                    perimeter -= 2;
                } else {
                    flag = true;
                }
            }
        }

        return perimeter;
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
