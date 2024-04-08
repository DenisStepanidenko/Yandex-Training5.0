package Lesson1.ProblemG;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();


    public static void main(String[] args) {
        int x = input.nextInt();
        int y = input.nextInt();
        int p = input.nextInt();

        // первым ходом мы уничтожаем казарму
        y = y - x;
        List<Integer> answers = new ArrayList<>();
        if (y <= 0) {
            System.out.println(1);
        } else {
            // рассмотрим случай, когда у нас бойцов меньше, чем то, что генерирует казарма
            if (x <= p) {
                // нам нужно за этот ход снести казарму
                if (y <= x) {
                    int answer = 1; // так как мы уже сделали первый ход
                    p = p - (x - y);
                    x = x - p;
                    if (x <= 0) {
                        System.out.println(-1);
                    } else {
                        answer++;
                        while (p > 0) {
                            p = p - x;
                            x = x - p;
                            if (x <= 0) {
                                answer = -1;
                                break;
                            } else {
                                answer++;
                            }
                        }
                        System.out.println(answer);
                    }
                } else {
                    System.out.println(-1);
                }
            } else {
                // когда у нас бойцов больше чем то, что генерирует казарма (x > p)
                int answer = 1;
                while (true) {
                    if (y > x) {
                        y = y - (x - p);
                        answer++;
                        if (y <= 0) {
                            break;
                        }
                    } else {
                        // здесь случай y <= x - значит есть шанс разрушить казарму за один ход, нужно пробовать

                        int potentialAnswer = getAnswer(x, y, p, answer);
                        if (potentialAnswer != -1) {
                            answers.add(potentialAnswer);
                        }
                        y = y - (x - p);
                        answer++;
                        if (y <= 0) {
                            break;
                        }
                    }
                }
                if (answers.isEmpty()) {
                    System.out.println(answer);
                } else {
                    int potentialMin = answers.stream().min(Integer::compareTo).get();
                    System.out.println(Math.min(answer, potentialMin));
                }

            }
        }

    }

    public static int getAnswer(int x, int y, int p, int currentAnswer) {
        // x - наши бойцы, p - вражеские, но сюда мы попадаем, когда казарма уже уничтожена
        p = p - (x - y);
        x = x - p;
        if (x <= 0) {
            return -1;
        } else {
            currentAnswer++;
            while (p > 0) {
                p = p - x;
                x = x - p;
                if (x <= 0) {
                    currentAnswer = -1;
                    break;
                } else {
                    currentAnswer++;
                }
            }
            return currentAnswer;
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
