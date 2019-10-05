package lesson1;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
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
        ArrayList<String> listOfString = read(inputName);
        ArrayList<Pair<Integer, String>> list = new ArrayList<>();
        for (String s : listOfString) {
            list.add(new Pair<>(timeToInt(s), s.split(" ")[1]));
        }
        list.sort(JavaTasks::compare);
        write((String[]) list.toArray(), outputName);
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

    static private ArrayList<String> read(String in) {
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(in))) {
            String s = reader.readLine();
            while (s != null) {
                list.add(s);
                s = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    static private void write(String[] text, String out) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(out))) {
            for (int i = 0; i < text.length; i++) {
                writer.write(text[i] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
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
        HashMap<String, ArrayList<String>> mapOfAdresses = splitting(read(inputName));
        ArrayList<String> result = new ArrayList<>();
        for (String key : mapOfAdresses.keySet()) {
            ArrayList<String> list = mapOfAdresses.get(key);
            list.sort(String::compareTo);
            StringBuilder sb = new StringBuilder(key);
            sb.append(" - ");
            sb.append(list.get(0));
            for (int i = 1; i < list.size(); i++) {
                sb.append(", ");
                sb.append(list.get(i));
            }
            result.add(sb.toString());
        }
        write((String[]) result.toArray(), outputName);
    }

    static private HashMap<String, ArrayList<String>> splitting(ArrayList<String> list) {
        list.sort(String::compareTo);
        HashMap<String, ArrayList<String>> result = new HashMap<>();
        for (String s : list) {
            String[] currentString = s.split("-");
            String adr = currentString[0].trim();
            String name = currentString[1].trim();
            if(result.get(adr) != null) {
                result.get(adr).add(name);
            } else {
                result.put(adr, new ArrayList<>());
                result.get(adr).add(name);
            }
        }
        return result;
    }



    /**
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
        throw new NotImplementedError();
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
        throw new NotImplementedError();
    }
}
