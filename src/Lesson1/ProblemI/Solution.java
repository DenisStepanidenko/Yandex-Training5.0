package Lesson1.ProblemI;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class Solution {
    static Reader input = new Reader();

    public static void main(String[] args) {
        int n = input.nextInt(); // количество праздников в году
        int year = input.nextInt();
        Map<Integer, String> test1 = new HashMap<>();
        test1.put(1, "Monday");
        test1.put(2, "Tuesday");
        test1.put(3, "Wednesday");
        test1.put(4, "Thursday");
        test1.put(5, "Friday");
        test1.put(6, "Saturday");
        test1.put(7, "Sunday");

        Map<String, Integer> test2 = new HashMap<>();
        test2.put("Monday", 1);
        test2.put("Tuesday", 2);
        test2.put("Wednesday", 3);
        test2.put("Thursday", 4);
        test2.put("Friday", 5);
        test2.put("Saturday", 6);
        test2.put("Sunday", 7);

        // читаем праздники

        //Map<Integer, String> weekends = new HashMap<>();
        List<Weekend> weekends = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int day = input.nextInt();
            String month = input.next();
            weekends.add(new Weekend(day, month));
        }

        // читаем первый день в году
        String newStart;
        newStart = input.nextLine();
        int offset = test2.get(newStart);

        // строим новую неделю начиная с newStart
        Map<Integer, String> newWeek = new TreeMap<>();
        for (Map.Entry<Integer, String> entry : test1.entrySet()) {
            int key = entry.getKey();
            key = key - offset + 1;
            if (key <= 0) {
                key = 7 + key;
            }
            newWeek.put(key, entry.getValue());
        }
        // теперь нужно добавить для каждого дня количество праздников, который на этот день выпадает
        Map<String, Integer> countOfWeekendByDays = new LinkedHashMap<>();
        for (Map.Entry<Integer, String> entry : newWeek.entrySet()) {
            countOfWeekendByDays.put(entry.getValue(), 0);
        }


        // теперь логика для не високосного года
        // для начала определим порядок для каждого года
        HashMap<String, Integer> startOfMonth = new HashMap<>();
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            // год високосный
            startOfMonth.put("January", 0);
            startOfMonth.put("February", 31);
            startOfMonth.put("March", 60);
            startOfMonth.put("April", 91);
            startOfMonth.put("May", 121);
            startOfMonth.put("June", 152);
            startOfMonth.put("July", 182);
            startOfMonth.put("August", 213);
            startOfMonth.put("September", 244);
            startOfMonth.put("October", 274);
            startOfMonth.put("November", 305);
            startOfMonth.put("December", 335);
        } else {
            // год не високосный
            startOfMonth.put("January", 0);
            startOfMonth.put("February", 31);
            startOfMonth.put("March", 59);
            startOfMonth.put("April", 90);
            startOfMonth.put("May", 120);
            startOfMonth.put("June", 151);
            startOfMonth.put("July", 181);
            startOfMonth.put("August", 212);
            startOfMonth.put("September", 243);
            startOfMonth.put("October", 273);
            startOfMonth.put("November", 304);
            startOfMonth.put("December", 334);
        }

        // теперь нужно добавить праздники
        for (Weekend weekend : weekends) {
            int off = (startOfMonth.get(weekend.getMonth()) + weekend.getDay()) % 7;
            if (off == 0) {
                off = 7;
            }

            // мы получили день недели, и теперь нужно прибавить праздник в этот день
            String nameOfDay = newWeek.get(off);
            countOfWeekendByDays.put(nameOfDay, countOfWeekendByDays.get(nameOfDay) + 1);

        }
//        for (Map.Entry<Integer, String> entry : weekends.entrySet()) {
//            int off = (startOfMonth.get(entry.getValue()) + entry.getKey()) % 7;
//            if (off == 0) {
//                off = 7;
//            }
//
//            // мы получили день недели, и теперь нужно прибавить праздник в этот день
//            String nameOfDay = newWeek.get(off);
//            countOfWeekendByDays.put(nameOfDay, countOfWeekendByDays.get(nameOfDay) + 1);
//        }


        // все праздники учтены, теперь нужно выбрать наилучший и наихудший вариант
        // найдём наилучший вариант
        String bestAnswer = "";
        int maxWeekend = -1;
        // нужно проверить високосный ли год
        String temp1 = "";
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            int index = (60 % 7);
            temp1 = newWeek.get(index);

        }

        int i = 1;
        for (Map.Entry<String, Integer> entry : countOfWeekendByDays.entrySet()) {
            if (i == 1) {
                // смотрим первый элемент, он 53 раза повторяется
                // попробуем сделать его праздником
                // тогда праздников будет:
                // ещё нужно проверить - вдруг этот день попадет на високосный
                if (temp1.equals(entry.getKey())) {
                    int currentWeekends = n + (53 - entry.getValue());
                    if (currentWeekends > maxWeekend) {
                        maxWeekend = currentWeekends;
                        bestAnswer = entry.getKey();
                    }
                    i++;
                } else {
                    int currentWeekends = n + (53 - entry.getValue());
                    if (currentWeekends > maxWeekend) {
                        maxWeekend = currentWeekends;
                        bestAnswer = entry.getKey();
                    }
                    i++;
                }
            } else {
                if (temp1.equals(entry.getKey())) {
                    int currentWeekends;
                    if (i == 2) {
                        currentWeekends = n + (53 - entry.getValue());
                    } else {
                        currentWeekends = n + (52 - entry.getValue());
                    }

                    if (currentWeekends > maxWeekend) {
                        maxWeekend = currentWeekends;
                        bestAnswer = entry.getKey();
                    }
                    i++;
                } else {
                    int currentWeekends = n + (52 - entry.getValue());
                    if (currentWeekends > maxWeekend) {
                        maxWeekend = currentWeekends;
                        bestAnswer = entry.getKey();
                    }
                    i++;
                }
            }
        }
        //System.out.println(countOfWeekendByDays);
        // теперь наидём наихудший вариант
        String worseAnswer = "";
        int minWeekend = Integer.MAX_VALUE;
        i = 1;
        for (Map.Entry<String, Integer> entry : countOfWeekendByDays.entrySet()) {
            if (i == 1) {
                // смотрим первый элемент, он 53 раза повторяется
                // попробуем сделать его праздником
                // тогда праздников будет:
                // ещё нужно проверить - вдруг этот день попадет на високосный
                if (temp1.equals(entry.getKey())) {
                    int currentWeekends = n + (53 - entry.getValue());
                    if (currentWeekends < minWeekend) {
                        minWeekend = currentWeekends;
                        worseAnswer = entry.getKey();
                    }
                    i++;
                } else {
                    int currentWeekends = n + (53 - entry.getValue());
                    if (currentWeekends < minWeekend) {
                        minWeekend = currentWeekends;
                        worseAnswer = entry.getKey();
                    }
                    i++;
                }
            } else {
                if (temp1.equals(entry.getKey())) {
                    int currentWeekends;
                    if (i == 2) {
                        currentWeekends = n + (53 - entry.getValue());
                    } else {
                        currentWeekends = n + (52 - entry.getValue());
                    }
                    if (currentWeekends < minWeekend) {
                        minWeekend = currentWeekends;
                        worseAnswer = entry.getKey();
                    }
                    i++;
                } else {
                    int currentWeekends = n + (52 - entry.getValue());
                    if (currentWeekends < minWeekend) {
                        minWeekend = currentWeekends;
                        worseAnswer = entry.getKey();
                    }
                    i++;
                }
            }
        }

        System.out.println(bestAnswer + " " + worseAnswer);

    }

    public static class Weekend {
        int day;
        String month;

        public Weekend(int day, String month) {
            this.day = day;
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public String getMonth() {
            return month;
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