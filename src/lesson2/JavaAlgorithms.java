package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;
import lesson1.JavaTasks;

import java.util.*;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     * <p>
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * ========================
     * Сложность O(n^2)
     * Доп. Память O(n^2)
     * =======================
     * <p>
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    static public String longestCommonSubstring(String firs, String second) {
        int[][] d = new int[firs.length() + 1][second.length() + 1];
        int max = -1, pos = 0;
        for (int i = 1; i < firs.length(); i++) {
            for (int j = 1; j < second.length(); j++) {
                if (firs.charAt(i - 1) == second.charAt(j - 1)) {
                    d[i][j] = d[i - 1][j - 1] + 1;
                }
                if (d[i][j] > max) {
                    max = d[i][j];
                    pos = i;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = max; i > 0; i--) {
            sb.append(firs.charAt(pos - i));
        }
        return sb.toString();
    }

    /**
     * Число простых чисел в интервале
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        throw new NotImplementedError();
    }

    /**================
     * Сложность O(n^2)
     * Память O(n)
     * ================
     * Балда
     * Сложная
     * <p>
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     * <p>
     * И Т Ы Н
     * К Р А Н
     * А К В А
     * <p>
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     * <p>
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     * <p>
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     * <p>
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */

    public enum Directions {
        LEFT(-1, 0), RIGHT(1, 0), UP(0, -1), DOWN(0, 1);

        private int x, y;

        Directions(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pair<Integer, Integer> getCoordsShift() {
            return new Pair<>(x, y);
        }
    }

    private static HashMap<Character, List<String>> mapOfWords;
    private static List<StringBuilder> field;
    private static List<Pair<Integer, Integer>> visited;
    private static List<String> result;

    //рекурсия отдает слово
    static public void find(int x, int y, int currentStep, List<String> list) {
        visited.add(new Pair<>(x, y));
        List<String> listOfWords = new ArrayList<>();
        for (Directions dir : Directions.values()) {
            int tempX = x + dir.getCoordsShift().getFirst();
            int tempY = y + dir.getCoordsShift().getSecond();
            if (field.get(0).length() > tempX && field.size() > tempY && tempY >= 0 && tempX >= 0 && !visited.contains(new Pair<>(tempX, tempY))) {
                for (String word : list) {
                    if (word.length() > currentStep && field.get(tempY).charAt(tempX) == word.charAt(currentStep)) {
                        listOfWords.add(word);
                    }
                    if (word.length() - 1 == currentStep && field.get(tempY).charAt(tempX) == word.charAt(currentStep)) {
                        result.add(word);
                    }
                }
            }
            if (!listOfWords.isEmpty()) {
                find(tempX, tempY, currentStep + 1, listOfWords);
            }
            listOfWords = new ArrayList<>();
            visited.remove(new Pair<>(x, y));
        }
    }

    static public Set<String> baldaSearcher(String inputName, Set<String> words) {
        visited = new ArrayList<>();
        mapOfWords = new HashMap<>();
        field = new ArrayList<>();
        result = new ArrayList<>();
        //contains pair LETTER and WORDS
        for (String s : words) {
            List<String> currentList = mapOfWords.get(s.charAt(0));
            if (currentList == null) {
                currentList = new ArrayList<>();
                currentList.add(s);
                mapOfWords.put(s.charAt(0), currentList);
            } else {
                currentList.add(s);
            }
        }
        //field contains of StringBuilders
        for (String s : JavaTasks.read(inputName)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i += 2) {
                sb.append(s.charAt(i));
            }
            field.add(sb);
        }

        for (int i = 0; i < field.size(); i++) {
            for (int j = 0; j < field.get(i).length(); j++) {
                if (mapOfWords.containsKey(field.get(i).charAt(j))) {
                    for (String word : mapOfWords.get(field.get(i).charAt(j))) {
                        if (word.length() == 1) {
                            result.add(word);
                        }
                    }
                    find(j, i, 1, mapOfWords.get(field.get(i).charAt(j)));
                }
            }
        }
        return new HashSet<>(result);
    }
}