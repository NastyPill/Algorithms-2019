package lesson1;

import kotlin.NotImplementedError;
import kotlin.Pair;
import sun.nio.cs.UTF_8;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * ====================================
     * Сложность в среднем случае O(nLog(n))
     * Сложность в худшем(sort работает, как qsort и на вход подается
     * такой массив, который заставляет работать qsort за n^2) O(n^2)
     * <p>
     * Память O(n)
     * <p>
     * ====================================
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     * <p>
     * Пример:
     * <p>
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static private BufferedReader bufferedReader;
    static private BufferedWriter bufferedWriter;
    final int HALF_DAY_IN_SECONDS = 12 * 60 * 60;

    static public void sortTimes(String inputName, String outputName) {
        List<String> listOfString = read(inputName);
        List<Pair<Integer, String>> list = new ArrayList<>();
        for (String s : listOfString) {
            list.add(new Pair<>(timeToInt(s), s));
        }
        list.sort(JavaTasks::compare);
        write(listOfSecond(list).toArray(), outputName);
    }

    static private List<String> listOfSecond(List<Pair<Integer, String>> list) {
        List<String> res = new ArrayList<>();
        for (Pair<Integer, String> p : list) {
            res.add(p.getSecond());
        }
        return res;
    }


    static private int compare(Pair<Integer, String> p1, Pair<Integer, String> p2) {
        return p1.getFirst().compareTo(p2.getFirst());
    }

    static private int timeToInt(String s) {
        final int HALF_DAY_IN_SECONDS = 12 * 60 * 60;
        final String regex = "\\d{2}:\\d{2}:\\d{2} [PA]M";
        if (!s.matches(regex)) {
            throw new IllegalArgumentException();
        }
        String[] time = s.split(" ");
        String[] nums = time[0].split(":");
        int hours = Integer.parseInt(nums[0]);
        int minutes = Integer.parseInt(nums[1]);
        int seconds = Integer.parseInt(nums[2]);
        if (hours == 12) {
            hours -= 12;
        }
        if (hours > 12 || minutes >= 60 || seconds >= 60) {
            throw new IllegalArgumentException();
        } else {
            int res = hours * 60 * 60 + minutes * 60 + seconds;
            if (time[1].equals("PM")) {
                return res + HALF_DAY_IN_SECONDS;
            } else {
                return res;
            }
        }
    }

    static public List<String> read(String in) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(in), StandardCharsets.UTF_8))) {
            String s = reader.readLine();
            while (s != null) {
                list.add(s);
                s = reader.readLine();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return list;
    }

    static public void write(Object[] text, String out) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out), StandardCharsets.UTF_8))) {
            for (int i = 0; i < text.length; i++) {
                writer.write(text[i] + "\n");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }


    /**
     * =================================
     * Сложность в среднем случае O(nLog(n))
     * Сложность в худшем(sort работает, как qsort и на вход подается
     * такой массив, который заставляет работать qsort за n^2) O(n^2)
     * <p>
     * Память O(n)
     * =================================
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        Map<String, List<String>> mapOfAdresses = splitting(read(inputName));
        List<String> result = new ArrayList<>();
        for (String key : mapOfAdresses.keySet()) {
            List<String> list = mapOfAdresses.get(key);
            StringBuilder sb = new StringBuilder(key);
            sb.append(" - ");
            sb.append(list.get(0));
            for (int i = 1; i < list.size(); i++) {
                sb.append(", ");
                sb.append(list.get(i));
            }
            result.add(sb.toString());
        }
        result.sort(JavaTasks::compare);
        write(result.toArray(), outputName);
    }

    static private int compare(String s1, String s2) {
        String[] adr1 = s1.split(" - ")[0].trim().split(" ");
        String[] adr2 = s2.split(" - ")[0].trim().split(" ");
        if (adr1[0].equals(adr2[0])) {
            return (Integer.parseInt(adr1[1]) > Integer.parseInt(adr2[1])) ? 1 : -1;
        } else {
            return adr1[0].compareTo(adr2[0]);
        }
    }

    static private Map<String, List<String>> splitting(List<String> list) {
        for (String s : list) {
            if (!s.matches("([A-Za-zsdа-яёА-ЯЁ]+ ){2}- (([а-яёА-ЯЁ]+)(-[А-Я][а-яё]+)?) \\d+")) {
                throw new IllegalArgumentException(s + " doesn't match");
            }
        }
        list.sort(String::compareTo);
        Map<String, List<String>> result = new HashMap<>();
        for (String s : list) {
            String[] currentString = s.split(" - ");
            String adr = currentString[1].trim();
            String name = currentString[0].trim();
            if (result.get(adr) != null) {
                result.get(adr).add(name);
            } else {
                result.put(adr, new ArrayList<>());
                result.get(adr).add(name);
            }
        }
        return result;
    }


    /**==================
     * Сложность O(n)
     * Память O(n)
     * ==================
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) {
        int[] array = new int[7732];
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputName), StandardCharsets.UTF_8))) {
            String s = reader.readLine();
            while (s != null) {
                if(!s.matches("-?\\d{1,3}(\\.\\d)?")){
                    throw new IllegalArgumentException();
                }
                array[(int) ((Double.parseDouble(s) + 273) * 10)]++;
                s = reader.readLine();
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputName), StandardCharsets.UTF_8))) {
            for (int i = 0; i < array.length; i++) {
                while (array[i] > 0) {
                    array[i]--;
                    writer.write( (double) (i - 2730) / 10 + "\n");
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Сортировка последовательности
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * ==============================
     * сложность О(n)
     * доп память не требуется, т.е. Память O(n)
     * ==============================
     * Соединить два отсортированных массива в один
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        int posInSecond = 0;
        int posInFirst = 0;
        for (int i = 0; i < second.length; i++) {
            if (second[i] != null) {
                posInSecond = i;
                break;
            }
        }
        int currentPosInResult = 0;
        while (posInFirst < first.length || posInSecond < second.length) {
            if (first[posInFirst].compareTo(second[posInSecond]) < 0) {
                second[currentPosInResult] = first[posInFirst];
                posInFirst++;
                currentPosInResult++;
            } else {
                second[currentPosInResult] = second[posInSecond];
                posInSecond++;
                currentPosInResult++;
            }
        }
    }
}
