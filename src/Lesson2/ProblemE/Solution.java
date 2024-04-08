package Lesson2.ProblemE;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int n = input.nextInt();
        List<Pair> positive = new ArrayList<>(); // для ягод с положительной (или 0) разностью
        List<Pair> negative = new ArrayList<>(); // для ягод с отрицательной разностью

        long positiveSumForC = 0; // сумма всех ci (ci = ai-bi)
        long maxHeightForNegative = -1; // максимальный подъём для отрицательной ягоды
        long indexForMaxHeightForNegative = 0;
        for (int i = 0; i < n; i++) {
            long a = input.nextLong();
            long b = input.nextLong();
            Pair current = new Pair(a, b, i + 1, a - b);
            if ((a - b) >= 0) {
                positiveSumForC += (a - b);
                positive.add(current);
            } else {
                if (a > maxHeightForNegative) {
                    maxHeightForNegative = a;
                    indexForMaxHeightForNegative = i + 1;
                }
                negative.add(current);
            }
        }

        if (!positive.isEmpty()) {
            // если есть положительные ягоды, то сначала съедаем обязательно их
            // причём нужно понять какую ягоду съесть последней, чтобы высота была максимальной
            StringBuilder answer = new StringBuilder();
            long maxHeight = -1; // это максимальная высота, будет являться ответом
            long indexOfMaxHeight = -1; // номер ягодки, которую нужно взять последней, чтобы была максимальная высота
            for (Pair pair : positive) {
                // посмотрим что будет если взять именно эту ягодку последней
                long previousHeight = positiveSumForC - pair.getC();
                if (previousHeight + pair.getA() > maxHeight) {
                    maxHeight = previousHeight + pair.getA();
                    indexOfMaxHeight = pair.getIndex();
                }
            }

            // теперь нужно сформировать список для порядка положительных
            for (Pair pair : positive) {
                if (!(pair.getIndex() == indexOfMaxHeight)) {
                    answer.append(pair.getIndex()).append(" ");
                }
            }
            answer.append(indexOfMaxHeight).append(" ");

            // теперь нужно поработать с отрицательными числами
            // по факту нам нужно из отрицательных первым съесть только ту, которая даёт максимальную дистанцию вверх
            if (!negative.isEmpty()) {
                // если есть отрицательные, то нужно изменить максимальную высоту
                if (maxHeight < positiveSumForC + maxHeightForNegative) {
                    maxHeight = positiveSumForC + maxHeightForNegative;
                }

                answer.append(indexForMaxHeightForNegative).append(" ");
                // и просто все остальные в любом порядке
                for (Pair pair : negative) {
                    if (!(pair.getIndex() == indexForMaxHeightForNegative)) {
                        answer.append(pair.getIndex()).append(" ");
                    }
                }
            }
            System.out.println(maxHeight);
            System.out.println(answer);
        }
        else{
            // если нет положительных ягод, значит есть хотя бы одна отрицательная
            long maxHeight = maxHeightForNegative;
            StringBuilder answer = new StringBuilder();
            answer.append(indexForMaxHeightForNegative).append(" ");
            for(Pair pair : negative){
                if(!(pair.getIndex() == indexForMaxHeightForNegative)){
                    answer.append(pair.getIndex()).append(" ");
                }
            }
            System.out.println(maxHeight);
            System.out.println(answer);
        }

    }

    static class Pair {
        private long a;
        private long b;
        private long index;
        private long c; // это будет разность между ai и bi

        public Pair(long a, long b, long index, long c) {
            this.a = a;
            this.b = b;
            this.index = index;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "x=" + a +
                    ", y=" + b +
                    '}';
        }

        public long getIndex() {
            return index;
        }

        public long getA() {
            return a;
        }

        public long getB() {
            return b;
        }

        public long getC() {
            return c;
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
