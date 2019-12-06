package lesson6;

import kotlin.NotImplementedError;
import org.graalvm.compiler.printer.CFGPrinterObserver;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Сложность O(n*m), где n и m - длины строк
     * Доп.Память O(n*m)
     * <p>
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    public static String longestCommonSubSequence(String first, String second) {
        int[][] d = new int[first.length() + 1][second.length() + 1];

        for (int i = first.length() - 1; i >= 0; i--)
            for (int j = second.length() - 1; j >= 0; j--) {

                if (first.charAt(i) == second.charAt(j)) {
                    d[i][j] = 1 + d[i + 1][j + 1];
                } else {
                    d[i][j] = Math.max(d[i + 1][j], d[i][j + 1]);
                }
            }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = 0;

        while (i < first.length() && j < second.length()) {
            if (first.charAt(i) == second.charAt(j)) {
                sb.append(first.charAt(i));
                i++;
                j++;

            } else {
                if (d[i + 1][j] >= d[i][j + 1]) {
                    i++;
                } else {
                    j++;
                }
            }

        }
        return sb.toString();
    }


    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        throw new NotImplementedError();
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        List<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputName));
            String s = br.readLine();
            while (s != null) {
                list.add(s);
                s = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int h = list.size();
        int w = list.get(0).split(" ").length;
        int[][] floor = new int[h + 2][w + 2];

        for (int i = 0; i < h; i++) {
            int[] temp = Arrays.stream(list.get(h - i - 1).split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = w - 1; j >= 0; j--) {
                floor[i][j] = temp[w - j - 1];
            }
        }

        for (int i = 1; i < h; i++)
            floor[i][0] = floor[i][0] + floor[i - 1][0];
        for (int j = 1; j < w; j++)
            floor[0][j] = floor[0][j] + floor[0][j - 1];

        for (int i = 1; i < h; i++) {
            for (int j = 1; j < w; j++) {
                int min = Math.min(floor[i - 1][j], floor[i][j - 1]);
                floor[i][j] += Math.min(min, floor[i - 1][j - 1]);
            }
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(floor[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(inputName);
        return floor[h - 1][w - 1];
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
